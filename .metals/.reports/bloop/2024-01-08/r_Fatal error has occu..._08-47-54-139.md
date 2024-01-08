error id: E+kG0yvLIVFcb3WcCfBxWQ==
### Bloop error:

Fatal error has occured within bloop bsp server, method: workspaceBuildTargets. Shutting down the server:
 org.eclipse.lsp4j.jsonrpc.JsonRpcException: java.io.IOException: Broken pipe
	at org.eclipse.lsp4j.jsonrpc.json.StreamMessageConsumer.consume(StreamMessageConsumer.java:72)
	at org.eclipse.lsp4j.jsonrpc.RemoteEndpoint.request(RemoteEndpoint.java:161)
	at org.eclipse.lsp4j.jsonrpc.services.EndpointProxy.invoke(EndpointProxy.java:91)
	at jdk.proxy4.$Proxy50.workspaceBuildTargets(Unknown Source)
	at scala.build.bsp.BuildServerForwardStubs.workspaceBuildTargets(BuildServerForwardStubs.scala:74)
	at scala.build.bsp.BuildServerForwardStubs.workspaceBuildTargets$(BuildServerForwardStubs.scala:9)
	at scala.build.bsp.BspServer.workspaceBuildTargets(BspServer.scala:219)
	at scala.build.bsp.BuildServerProxy.workspaceBuildTargets(BuildServerProxy.scala:26)
	at java.lang.reflect.Method.invoke(Method.java:568)
	at org.eclipse.lsp4j.jsonrpc.services.GenericEndpoint.lambda$null$0(GenericEndpoint.java:65)
	at org.eclipse.lsp4j.jsonrpc.services.GenericEndpoint.request(GenericEndpoint.java:120)
	at org.eclipse.lsp4j.jsonrpc.RemoteEndpoint.handleRequest(RemoteEndpoint.java:261)
	at org.eclipse.lsp4j.jsonrpc.RemoteEndpoint.consume(RemoteEndpoint.java:190)
	at org.eclipse.lsp4j.jsonrpc.json.StreamMessageProducer.handleMessage(StreamMessageProducer.java:194)
	at org.eclipse.lsp4j.jsonrpc.json.StreamMessageProducer.listen(StreamMessageProducer.java:94)
	at org.eclipse.lsp4j.jsonrpc.json.ConcurrentMessageProcessor.run(ConcurrentMessageProcessor.java:113)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539)
	at java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
	at java.lang.Thread.run(Thread.java:833)
	at com.oracle.svm.core.thread.PlatformThreads.threadStartRoutine(PlatformThreads.java:705)
	at com.oracle.svm.core.posix.thread.PosixPlatformThreads.pthreadStartRoutine(PosixPlatformThreads.java:202)
Caused by: java.io.IOException: Broken pipe
	at com.oracle.svm.jni.JNIJavaCallWrappers.jniInvoke_VA_LIST_IOException_constructor_dfe3139624312c30e7f76f0e723ef6192050fcf4(JNIJavaCallWrappers.java:0)
	at sun.nio.ch.FileDispatcherImpl.write0(FileDispatcherImpl.java)
	at sun.nio.ch.SocketDispatcher.write(SocketDispatcher.java:62)
	at sun.nio.ch.IOUtil.writeFromNativeBuffer(IOUtil.java:132)
	at sun.nio.ch.IOUtil.write(IOUtil.java:97)
	at sun.nio.ch.IOUtil.write(IOUtil.java:53)
	at sun.nio.ch.SocketChannelImpl.write(SocketChannelImpl.java:532)
	at java.nio.channels.Channels.writeFullyImpl(Channels.java:74)
	at java.nio.channels.Channels.writeFully(Channels.java:93)
	at java.nio.channels.Channels$1.write(Channels.java:171)
	at java.io.OutputStream.write(OutputStream.java:127)
	at org.eclipse.lsp4j.jsonrpc.json.StreamMessageConsumer.consume(StreamMessageConsumer.java:67)
	... 22 more

#### Short summary: 

Fatal error has occured within bloop bsp server, method: workspaceBuildTargets. Shutting down the se...