package org.sakaiproject.content.api;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.sakaiproject.entity.api.Reference;

/**
 * ResourceToolActionPipe provides a conduit through which ResourcesAction and an 
 * unknown helper may communicate about the execution of ResourceToolActions in which
 * the registered action specifies that some part of the action is handled by a helper.
 * 
 * ResourceToolActionPipe has a set of methods through which ResourcesAction can pass 
 * information to a helper about the current state of the entity (or entities?) involved 
 * in an action ("setContent", "getContent", "setMimeType", "getMimeType", etc).  It has 
 * another set of methods through which the helper passes back values that may or may 
 * not have been updated as a result of the action ("setRevisedContent", "getRevisedContent", 
 * "setRevisedMimeType", "getRevisedMimeType", etc). If a value is not changed by the action,
 * the helper should use an appropriate setter to indicate the revised value is the same
 * as the original value.  For example:
 * 
 *   pipe.setRevisedMimeType( pipe.getMimeType() );
 *   
 * Otherwise, the getter for the revised value will return null, and ResourcesAction will
 * set unset the property. ResourceToolActionPipe also has a few methods through which the 
 * helper can report whether the action was canceled or an error was encountered.  
 * 
 * @see org.sakaiproject.content.api.ResourceTypeRegistry
 * @see org.sakaiproject.content.api.ResourceToolAction
 * @see org.sakaiproject.content.api.InteractionAction
 */
public interface ResourceToolActionPipe 
{
	/**
	 * @return
	 */
	public ResourceToolAction getAction();
	
	/**
	 * Used by helper to access current value of resource's "content". Return will be 
	 * empty array to indicate no content is defined for resource or null to indicate 
	 * that content should be accessed as an OutputStream because of size or type of content. 
	 * @return
	 */
	public byte[] getContent();
	
	/**
	 * Used by helper to access a Reference object that fully identifies an existing ContentResource
	 * involved in this action.  If the action is "create", the reference identifies a collection
	 * within which a new resource is being created.  Otherwise the reference identifies a resource
	 * of the type involved in this action. 
	 * @return
	 */
	public Reference getContentEntityReference();
	
	/**
	 * Used by helper to access current value of resource's "content" in cases where size 
	 * or type of content requires stream access. Return may be null to indicate 
	 * that content should be accessed as a byte array. 
	 * @return
	 */
	public OutputStream getContentStream();
	
	/**
	 * Used by helper to access current mimetype of resource. Null value or empy string 
	 * indicates that mimetype is irrelevant or unavailable.
	 * @return
	 */
	public String getMimeType();
	
	/**
	 * Used by the Resources tool to retrieve error message that will be shown to user in 
	 * its list view after completion of helper activity in which "error encountered" is set 
	 * to true and an error is reported. 
	 * @return
	 */
	public String getErrorMessage();
	
	/**
	 * @return
	 */
	public String getHelperId();
	
	/**
	 * Used by helper to access the initialization-id for this action, if an initialization-id
	 * was returned when ResourcesAction invoked the InteractionAction.initializeAction() method. 
	 * The return value of this method will be null if an initialization-id was not supplied by
	 * that method.
	 * @return
	 */
	public String getInitializationId();
	
	/**
	 * Used by helper to access current value of any resource property that has been specified 
	 * by registrant in InteractionAction. Returns null if property value is not defined. Otherwise
	 * returns a String or a List of Strings
	 * @return
	 */
	public Object getPropertyValue(String name);
	
	/**
	 * Used by ResourceAction to access helper's revised value of resource's "content". An empty 
	 * array indicates that the resource has no content.  A null value indicates that the content
	 * should be accessed as a stream.
	 * @return
	 */
	public byte[] getRevisedContent();
	
	/**
	 * Used by ResourceAction to access helper's revised value of resource's "content". If both 
	 * the byte-array and the stream are null, or if the byte-array is null and the stream does
	 * not contain any data, the resource is assumed to have no content. 
	 * @return
	 */
	public InputStream getRevisedContentStream();
	
	/**
	 * Used by ResourceAction to access helper's revised value for mimetype of resource.
	 * @return
	 */
	public String getRevisedMimeType();
	
	/**
	 * Used by ResourceAction to access helper's revisions to values of resource properties. 
	 * @return
	 */
	public Map getRevisedResourceProperties();

	/**
	 * May be accessed by ResourcesAction after completion of helper activity to determine 
	 * whether action was canceled.  If the action was not canceled and no error was encountered, 
	 * ResourcesAction will assume that the action completed successfully.
	 * @return
	 */
	public boolean isActionCanceled();
	
	/**
	 * @return
	 */
	public boolean isActionCompleted();
	
	/**
	 * May be accessed by ResourcesAction after completion of helper activity to determine 
	 * whether an error was encountered.  If the action was not canceled and no error was 
	 * encountered, ResourcesAction will assume that the action completed successfully.
	 * @return
	 */
	public boolean isErrorEncountered();
	
	/**
	 * Used by helper to indicate that User canceled the action and the action was not completed.
	 * @param actionCanceled
	 */
	public void setActionCanceled(boolean actionCanceled);
	
	/**
	 * @param actionCompleted
	 */
	public void setActionCompleted(boolean actionCompleted);
	
	/**
	 * Used by ResourcesAction to provide helper with current value of resource's "content".
	 * @return
	 */
	public void setContent(byte[] content);
	
	/**
	 * Used by ResourcesAction to provide a Reference that fully identifies an existing ContentResource
	 * involved in this action.  If the action is "create", the reference identifies a collection
	 * within which a new resource is being created.  Otherwise the reference identifies a resource
	 * of the type involved in this action. 
	 * @param reference
	 */
	public void setContentEntityReference(Reference reference);
	
	/**
	 * Used by ResourcesAction to provide helper with alternative access to current value of resource's "content".
	 * @param content
	 */
	public void setContentStream(OutputStream ostream);
	
	/**
	 * Used by ResourcesAction to provide helper with mimetype of resource. 
	 * @param ostream
	 */
	public void setMimeType(String type);
	
	/**
	 * Used by helper to indicate that an error was encountered which prevented completion 
	 * of the action.
	 * @param actionCanceled
	 */
	public void setErrorEncountered(boolean errorEncountered);
	
	/**
	 * Used by helper to report error to user after completion of helper's portion of wizard. 
	 * Most errors in the helper should be dealt with in the helper. This is for unusual errors
	 * that can not be dealt with in the helper and are tossed back to the Resources tool. 
	 * If a message is reported and "error encountered" is set to true, the Resources tool
	 * will display the message in its list view. The message should be localized (i.e. read
	 * from a resource bundle).
	 * @param msg
	 */
	public void setErrorMessage(String msg);
	
	/**
	 * Used by ResourcesAction to provide a value for the initialization-id.  Should be initialized
	 * to the value returned by the InteractionAction.initializeAction() method (possibly null or
	 * an empty string is this information is not needed by the helper). 
	 * @param 
	 */
	public void setInitializationId(String id);
	
	/**
	 * Used by ResourcesAction to provide helper with current value of a requested resource property
	 * whose value is a List of Strings.
	 * @param key
	 * @param list
	 */
	public void setResourceProperty(String key, List list);

	/**
	 * Used by ResourcesAction to provide helper with current value of a requested resource property
	 * whose value is a single String.
	 * @param name
	 * @param value
	 */
	public void setResourceProperty(String name, String value);
	
	/**
	 * Used by helper to provide ResourcesAction with revised value of resource's "content".
	 * @param content
	 */
	public void setRevisedContent(byte[] content);

	/**
	 * Used by helper to provide ResourcesAction with revised value of resource's "content".
	 * @param istream
	 */
	public void setRevisedContentStream(InputStream istream);
	
	/**
	 * Used by helper to provide ResourcesAction with revised mimetype of resource. 
	 * Mimetype will not be referenced if param is null.
	 * @param type
	 */
	public void setRevisedMimeType(String type);

	/**
	 * Used by helper to provide ResourcesAction with revised value for a resource property
	 * whose value is a List of Strings.  Any property other than "live" properties can be set 
	 * with this method. If action definition names the property and it is not set by this method, 
	 * current value(s) will be removed.  
	 * @param name
	 * @param list
	 */
	public void setRevisedResourceProperty(String name, List list);
	
	/**
	 * Used by helper to provide ResourcesAction with revised value for a resource property.
	 * Any property other than "live" properties can be set with this method. 
	 * If action definition names the property and it is not set by this method,  
	 * current value will be removed.  
	 * @param name
	 * @param value
	 */
	public void setRevisedResourceProperty(String name, String value);

	/**
	 * @param helperId
	 */
	public void setHelperId(String helperId);
}
