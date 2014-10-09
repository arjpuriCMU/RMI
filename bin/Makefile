JFLAGS =
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Client/Client.java \
	Client/EchoClient.java \
	Client/Integer440Client.java \
	Client/nthPrimeClient.java \
	Example/EchoInterface.java \
	Example/EchoObject.java \
	Example/Integer440Interface.java \
	Example/Integer440Object.java \
	Example/nthPrimeInterface.java \
	Example/nthPrimeObject.java \
	Messages/MethodCallMessage.java \
	Messages/MethodReturnMessage.java \
	Messages/RegistryJobMessage.java \
	Registry/RemoteHandler.java \
	Registry/RemoteObjectReference.java \
	Registry/RMIRegistry.java \
	RMIServer/Remote440.java \
	RMIServer/RMIServer.java \
	RMIServer/ServerWrapper.java \
	util/Group.java \
	util/Info.java \
	util/Job.java \
	

RMCLASSES = \
	Client/Client.class \
	Client/EchoClient.class \
	Client/Integer440Client.class \
	Client/nthPrimeClient.class \
	Example/EchoInterface.class \
	Example/EchoObject.class \
	Example/Integer440Interface.class \
	Example/Integer440Object.class \
	Example/nthPrimeInterface.class \
	Example/nthPrimeObject.class \
	Messages/MethodCallMessage.class \
	Messages/MethodReturnMessage.class \
	Messages/RegistryJobMessage.class \
	Registry/RemoteHandler.class \
	Registry/RemoteObjectReference.class \
	Registry/RMIRegistry.class \
	RMIServer/Remote440.class \
	RMIServer/RMIServer.class \
	RMIServer/ServerWrapper.class \
	util/Group.class \
	util/Info.class \
	util/Job.class \


default: classes

classes: $(CLASSES:.java=.class)

clean:	
	rm $(RMCLASSES)
