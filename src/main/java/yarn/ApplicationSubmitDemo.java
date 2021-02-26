package yarn;

import org.apache.hadoop.yarn.api.records.*;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.client.api.YarnClientApplication;
import org.apache.hadoop.yarn.exceptions.YarnException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Fenger
 * @date 2021-02-24 11:11
 */
public class ApplicationSubmitDemo {

    public static void main(String[] args) {

        YarnClient yarnClient = YarnClient.createYarnClient();
        yarnClient.init(null);
        yarnClient.start();

        try {
            // 申请一个Application
            // 返回值中包含资源提交路径hdfs://...及applicationId
            YarnClientApplication app = yarnClient.createApplication();

            // todo 准备所需资源
            ApplicationSubmissionContext appContext = app.getApplicationSubmissionContext();
            appContext.setResource(Resource.newInstance(512, 2));
            appContext.setApplicationName("demo");
            appContext.setPriority(Priority.newInstance(0));
            appContext.setQueue("default");
//            appContext.setAMContainerResourceRequest(ResourceRequest.newInstance(Priority.newInstance(0), "", Resource.newInstance(1024, 2), 1));

            // 设置应用程序需要的资源，及启动Container命令，即在Container中启动一个JVM
            // todo 需要自定义实现Application Master的逻辑
            Map<String, LocalResource> localResources = new HashMap<>();
            Map<String, String> env = new HashMap<>();
            List<String> commands = new ArrayList<>();
            ContainerLaunchContext containerLaunchContext = ContainerLaunchContext.newInstance(localResources, env, commands, null, null, null);
            appContext.setAMContainerSpec(containerLaunchContext);

            // 提交任务，等待Yarn调度
            ApplicationId applicationId = yarnClient.submitApplication(appContext);

            // monitor
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ApplicationReport applicationReport = yarnClient.getApplicationReport(applicationId);
                System.out.println(applicationReport);
            }

        } catch (YarnException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
