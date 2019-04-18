package com.coderay.netty.demo.pipeline;

abstract class AbstractHandler {
    /**
     * 处理器，这个处理器就做一件事情，在传入的字符串中增加一个尾巴..
     */
    abstract void doHandler(HandlerChainContext handlerChainContext, Object arg0); // handler方法

}
