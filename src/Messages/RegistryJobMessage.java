package Messages;

import java.io.Serializable;

import util.Job;
import Registry.RemoteObjectReference;

public class RegistryJobMessage implements Serializable {
	private static final long serialVersionUID = -1482755404667747214L;
	private Job job;
    private String object_id;
	private RemoteObjectReference remote_reference;
    private String[] list;
    private boolean exception = false;
    private String errorMessage;

	
	public RegistryJobMessage(Job job, String object_id){
		super();
		this.job = job;
		this.object_id = object_id;
	}
	
	public Job getJob(){
		return this.job;
	}

    public String getObjectId(){
        return this.object_id;
    }

    public void setRef(RemoteObjectReference ror) {this.remote_reference = ror;}

    /* If exception throw it, otherwise return ROR */
	public RemoteObjectReference getRef() throws Exception {
		if(exception)
            throw new Exception(errorMessage);
        return this.remote_reference;
	}

    public void setList(String[] list)
    {
        this.list = list;
    }

    /* If exception throw it, otherwise return list */
    public String[] getList() throws Exception {
        if(exception)
            throw new Exception(errorMessage);
        return this.list;
    }

    /* Notes that Exception was thrown by Server */
    public void sendException(String errorMessage)
    {
        this.exception = true;
        this.errorMessage = errorMessage;
    }
}
