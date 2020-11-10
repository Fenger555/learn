package util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.transfer.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname S3Utils
 * @Description
 * @Date 2020/7/14 12:14 上午
 * @Created by Vlad_III
 */
public class S3Utils {
    private static Logger logger = LoggerFactory.getLogger(S3Utils.class);

    /**
     * 获取ceph S3连接Client
     * @param accessKeyId
     * @param secretAccessKey
     */
    public static AmazonS3 getS3Client(String accessKeyId, String secretAccessKey, String endpointUrl) {

        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpointUrl,null))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

        return s3Client;
    }


    public static void uploadStream(AmazonS3 s3Client, String bucketName, String filePath, InputStream inputStream) throws InterruptedException {
        TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(s3Client).build();
        PutObjectRequest por = new PutObjectRequest(bucketName, filePath, inputStream,  new ObjectMetadata());
        AccessControlList acl = new AccessControlList();
        acl.grantPermission(GroupGrantee.AllUsers, Permission.FullControl);
        por.setAccessControlList(acl);
        Upload upload = transferManager.upload(por);
        try {
            upload.waitForCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("success");
        transferManager.shutdownNow(false);
    }

    public static void uploadFile(AmazonS3 s3Client, String bucketName, String s3filePath, String filePath) {
        TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(s3Client).build();
        File file = new File(filePath);
        String fileName = StringUtils.substringAfterLast(filePath, "/");
        PutObjectRequest por = new PutObjectRequest(bucketName, s3filePath + "/" + fileName, file);
        AccessControlList acl = new AccessControlList();
        acl.grantPermission(GroupGrantee.AllUsers, Permission.FullControl);
        por.setAccessControlList(acl);
        Upload upload = transferManager.upload(por);
        try {
            upload.waitForCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        transferManager.shutdownNow(false);
    }

    public static void cpFile(AmazonS3 s3Client, String fromBucket, String fromFilePath, String toBucket, String toFilePath) {
        TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(s3Client).build();
        Copy copy = transferManager.copy(fromBucket, fromFilePath, toBucket, toFilePath);
        try {
            copy.waitForCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        transferManager.shutdownNow(false);
    }

    public static String getS3FileMd5(AmazonS3 s3Client, String bucketName, String s3File) {
        String md5 = "";
        try {
            md5 = s3Client.getObjectMetadata(bucketName, s3File).getETag();
        } catch (Exception e) {
            logger.info("bucket:" + bucketName + ",s3PathOrFile:" + s3File + "的文件不存在!");
        }
        return md5;
    }

    public static void uploadFiles(AmazonS3 s3Client,  String bucketName, String filePath, List<String> filePaths) {
        TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(s3Client).build();
        for (String path : filePaths){
            File file = new File(path);
            String fileName = StringUtils.substringAfterLast(path, "/");
            PutObjectRequest por = new PutObjectRequest(bucketName, filePath + "/" + fileName, file);
            AccessControlList acl = new AccessControlList();
            acl.grantPermission(GroupGrantee.AllUsers, Permission.FullControl);
            por.setAccessControlList(acl);
            Upload upload = transferManager.upload(por);
            try {
                upload.waitForCompletion();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        transferManager.shutdownNow(false);
    }

    /**
     * 批量上传文件
     * @param s3Client
     * @param filePaths
     * @param bucketName
     * @param keyPrefix
     */
    public static void uploadFileList(AmazonS3 s3Client, String dirPath, List<String> filePaths, String bucketName,
                                      String keyPrefix) {
        List<File> files = new ArrayList<>();
        for (String path : filePaths) {
            files.add(new File(path));
        }
        TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(s3Client).build();
        try {
            MultipleFileUpload multipleFileUpload = transferManager.uploadFileList(bucketName,
                    keyPrefix, new File("."), files, (file, objectMetadata) -> objectMetadata.setHeader("x-amz-acl","public-read"));
            multipleFileUpload.waitForCompletion();
        } catch (AmazonServiceException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        transferManager.shutdownNow(false);
    }

    /**
     * 上传整个文件夹
     * @param s3Client
     * @param dirPath
     * @param bucketName
     * @param keyPrefix
     * @param recursive
     */
    public static void uploadDir(AmazonS3 s3Client, String dirPath, String bucketName,
                                 String keyPrefix, boolean recursive) {

        TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(s3Client).build();
        try {
            MultipleFileUpload multipleFileUpload = transferManager.uploadDirectory(bucketName,
                    keyPrefix, new File(dirPath), recursive, (file, objectMetadata) -> objectMetadata.setHeader("x-amz-acl","public-read"));
            multipleFileUpload.waitForCompletion();
        } catch (AmazonServiceException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        transferManager.shutdownNow(false);
    }

}
