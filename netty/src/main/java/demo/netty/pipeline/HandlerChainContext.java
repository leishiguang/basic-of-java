package demo.netty.pipeline;

/**
 * handler 上下文
 */
public class HandlerChainContext {

    HandlerChainContext next; // 下一个节点
    private AbstractHandler handler;

    HandlerChainContext(AbstractHandler handler) {
        this.handler = handler;
    }

    void handler(Object arg0) {
        this.handler.doHandler(this, arg0);
    }

    /**
     * 继续执行下一个
     */
    void runNext(Object arg0) {
        if (this.next != null) {
            this.next.handler(arg0);
        }
    }
}
