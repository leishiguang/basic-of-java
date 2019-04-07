package com.coderay.netty.pipeline;

/**
 * 链表形式的责任链，netty 就是这种方式
 */
public class PipelineDemo {

    private HandlerChainContext head = new HandlerChainContext(new AbstractHandler() {
        @Override
        void doHandler(HandlerChainContext handlerChainContext, Object arg0) {
            handlerChainContext.runNext(arg0);
        }
    });

    private void requestProcess(Object arg0){
        this.head.handler(arg0);
    }

    private void addLast(AbstractHandler handler){
        HandlerChainContext context = head;
        while (context.next !=null){
            context = context.next;
        }
        context.next = new HandlerChainContext(handler);
    }

    public static void main(String[] args){
        PipelineDemo pipelineDemo = new PipelineDemo();
        pipelineDemo.addLast(new Handler2());
        pipelineDemo.addLast(new Handler1());
        pipelineDemo.addLast(new Handler1());
        pipelineDemo.addLast(new Handler2());
        pipelineDemo.requestProcess("火车呜呜呜~~");
        pipelineDemo.requestProcess("啦啦啦~~");
    }

}
