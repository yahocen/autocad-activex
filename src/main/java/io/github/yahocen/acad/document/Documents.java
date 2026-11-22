package io.github.yahocen.acad.document;

import io.github.yahocen.acad.common.Disp;
import cn.hutool.core.thread.ThreadUtil;
import io.github.yahocen.jacob.com.ComFailException;
import io.github.yahocen.jacob.com.Dispatch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.SneakyThrows;
import io.github.yahocen.acad.Application;
import io.github.yahocen.acad.common.ComCollection;

import java.util.concurrent.TimeoutException;

/**
 * AutoCAD文档集合
 *
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "Documents AutoCAD文档集合",
    description = "AutoCAD中所有打开的文档集合"
)
public class Documents extends ComCollection<Document> {

    @Getter
    private final Application application;

    public Documents(Application application) {
        super(Dispatch.call(application.getAcadApp(), "Documents").toDispatch());
        this.application = application;
    }

    @Override
    protected Document wrap(Dispatch dispatch) {
        return new Document(dispatch);
    }

    /**
     * 创建新文档
     */
    @Operation(summary = "创建新文档", description = "创建新文档")
    public Document add() {
        return waitForInit(new Document(Disp.call(getClass(), dispatch, "Add").toDispatch()));
    }

    @Operation(summary = "打开 DWG 文件", description = "打开 DWG 文件")
    public Document open(@Parameter(description = "DWG 文件地址") String filePath) {
        return waitForInit(new Document(Disp.call(getClass(), dispatch, "Open", filePath).toDispatch()));
    }

    @SneakyThrows
    private Document waitForInit(Document doc) {
        long startTime = System.currentTimeMillis();
        while(true) {
            try {
                doc.getModelSpace();
                ThreadUtil.sleep(200);
                return doc;
            } catch(ComFailException e) {
                if(System.currentTimeMillis() - startTime > 5000) {
                    throw new TimeoutException("文档打开超时");
                }
                ThreadUtil.sleep(100);
            }
        }
    }

}
