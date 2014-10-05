package Registry;

import java.io.Serializable;

public class RegistryJobMessage implements Serializable {
	private Job job;
	private RemoteObjectReference remote_reference;
	private String object_id;
	
	public RegistryJobMessage(Job job, RemoteObjectReference ref, String object_id){
		super();
		this.job = job;
		this.remote_reference = ref;
		this.object_id = object_id;
	}
	
	public Job getJob(){
		return this.job;
	}
	
	public RemoteObjectReference getRef(){
		return this.remote_reference;
	}
	
	public String getObjectId(){
		return this.object_id;
	}
}
