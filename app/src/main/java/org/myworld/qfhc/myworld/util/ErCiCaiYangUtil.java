package org.myworld.qfhc.myworld.util;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * @描述：
 * @创建人： 杰
 * @创建时间: 2016/4/8 16:15
 * @备注：
 */
public class ErCiCaiYangUtil {



    public static void ErCiCaiYang(String path,SimpleDraweeView simpleDraweeView) {
        int width = 200, height = 200;
        SimpleDraweeView sdv=simpleDraweeView;
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(path))
                .setResizeOptions(new ResizeOptions(width, height))
                .build();
        PipelineDraweeController controller = (PipelineDraweeController)(Fresco.newDraweeControllerBuilder()
                .setOldController(simpleDraweeView.getController())
                .setImageRequest(request)
                .build());

        simpleDraweeView.setController(controller);
    }
    public static void CustomErCiCaiYang(int size,String path,SimpleDraweeView simpleDraweeView) {
        int width = size, height = size;
        SimpleDraweeView sdv=simpleDraweeView;
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(path))
                .setResizeOptions(new ResizeOptions(width, height))
                .build();
        PipelineDraweeController controller = (PipelineDraweeController)(Fresco.newDraweeControllerBuilder()
                .setOldController(simpleDraweeView.getController())
                .setImageRequest(request)
                .build());

        simpleDraweeView.setController(controller);
    }

}
