package base_connectivity;

import gui.UploadFrame;

import javax.swing.JList;


import base_connectivity.MSS_RQ_Admin;
import base_connectivity.MSS_RQ_CxListFiller;
import base_connectivity.MSS_RQ_Request;
import base_connectivity.MSS_RQ_TableDescriptor;
import base_connectivity.MSS_RQ_User;
import base_connectivity.MSS_RQ_XMLtoTableDescriptor;

// For specific panel - groups
public class SP_ServerDispatcher implements ServerDispatcher {
	private MSS_RQ_Admin reqHandlerAdmin;
	private JList list;
	//private String URL;
	private MSS_RQ_TableDescriptor QuizTDesc;
	private MSS_RQ_XMLtoTableDescriptor QuizT_XML_Desc;
	private MSS_RQ_CxListFiller QuizUpdater;
	public SP_ServerDispatcher(JList lst)
	{
		list = lst;
		//this.URL = UploadFrame.URL;	
		reqHandlerAdmin = new MSS_RQ_Admin("Tool", UploadFrame.login, UploadFrame.pswd);
	}
	@Override
	public void add(Object [] params) {
		QuizTDesc = new MSS_RQ_TableDescriptor(new String[]{"�"}, new Class[]{Integer.class});
		QuizT_XML_Desc = new MSS_RQ_XMLtoTableDescriptor(new String[]{"name","ID"});
		QuizUpdater = new MSS_RQ_CxListFiller(QuizTDesc,QuizT_XML_Desc, ListGroupsElementData.class);
		@SuppressWarnings("unused")
		String xml_arr_ans = MSS_RQ_Request.http_request(reqHandlerAdmin.makeGroup((String)params[0],(String)params[1]),UploadFrame.URL);
		//QuizUpdater.updateData(xml_arr_ans);
		//QuizUpdater.fillCxList(list);
	}
	public void addGroup(Object [] params) {
		//QuizTDesc = new MSS_RQ_TableDescriptor(new String[]{"�"}, new Class[]{Integer.class});
		//QuizT_XML_Desc = new MSS_RQ_XMLtoTableDescriptor(new String[]{"name","ID"});
		//QuizUpdater = new MSS_RQ_CxListFiller(QuizTDesc,QuizT_XML_Desc, ListGroupsElementData.class);
		@SuppressWarnings("unused")
		String xml_arr_ans = MSS_RQ_Request.http_request(reqHandlerAdmin.makeGroup
				(
						Integer.parseInt((String)params[0]),
						Integer.parseInt((String)params[1]),
						(String)params[2],
						(String)params[3]
				),UploadFrame.URL);
		//QuizUpdater.updateData(xml_arr_ans);
		//QuizUpdater.fillCxList(list);
	}
	public void addGroupLazy(Object [] params) {
		MSS_RQ_Request.http_request(reqHandlerAdmin.makeGroup
				(
						(String)params[0],
						(String)params[1]
				),UploadFrame.URL);
	}
	public void addGroupID(Object [] params) {
		//QuizTDesc = new MSS_RQ_TableDescriptor(new String[]{"�"}, new Class[]{Integer.class});
		//QuizT_XML_Desc = new MSS_RQ_XMLtoTableDescriptor(new String[]{"name","ID"});
		//QuizUpdater = new MSS_RQ_CxListFiller(QuizTDesc,QuizT_XML_Desc, ListGroupsElementData.class);
		@SuppressWarnings("unused")
		String xml_arr_ans = MSS_RQ_Request.http_request(reqHandlerAdmin.makeGroup
				(
						Integer.parseInt((String)params[0]),
						(String)params[1],
						(String)params[2]
				),UploadFrame.URL);
		//QuizUpdater.updateData(xml_arr_ans);
		//QuizUpdater.fillCxList(list);
	}
	public void registerUser(Object [] params) {
			@SuppressWarnings("unused")
		String xml_arr_ans = MSS_RQ_Request.http_request(reqHandlerAdmin.registerUser
						(
								Integer.parseInt((String)params[0]),
								Integer.parseInt((String)params[1]),
								(String)params[2],
								(String)params[3]
						),UploadFrame.URL);

	}	
	public void registerUserAuto(Object [] params) {
		@SuppressWarnings("unused")
	String xml_arr_ans = MSS_RQ_Request.http_request(reqHandlerAdmin.registerUser
					(
							Integer.parseInt((String)params[0]),
							(String)params[1],
							(String)params[2]
					),UploadFrame.URL);

	}
	public void registerUserBulkEnable() {
		reqHandlerAdmin.enableBulk();
	}
	public void registerUserBulkDisable() {
		reqHandlerAdmin.disableBulk();
	}
	public void registerUserBulkAdd(Object [] params) {
		reqHandlerAdmin.registerUser
		(
				Integer.parseInt((String)params[0]),
				(String)params[1],
				(String)params[2]
		);
	}
	public String registerUserBulkProcess() {
		return MSS_RQ_Request.http_request(reqHandlerAdmin.processBulk(),UploadFrame.URL);
	}
	public void enroll(Object [] params) {
		//QuizTDesc = new MSS_RQ_TableDescriptor(new String[]{"�"}, new Class[]{Integer.class});
		//QuizT_XML_Desc = new MSS_RQ_XMLtoTableDescriptor(new String[]{"name","ID"});
		//QuizUpdater = new MSS_RQ_CxListFiller(QuizTDesc,QuizT_XML_Desc, ListGroupsElementData.class);
		@SuppressWarnings("unused")
		String xml_arr_ans = MSS_RQ_Request.http_request(reqHandlerAdmin.enrollToGroup
						(
								Integer.parseInt((String)params[0]),
								Integer.parseInt((String)params[1]),
								(String)params[2]
						),UploadFrame.URL);
		//QuizUpdater.updateData(xml_arr_ans);
		//QuizUpdater.fillCxList(list);
	}
	public void subscribe(String idUser,String pswd,String idTask) {
		MSS_RQ_User reqUser = new MSS_RQ_User("TULA", idUser, pswd);
		MSS_RQ_Request.http_request(reqUser.subscribeTask(Integer.parseInt(idTask)),UploadFrame.URL);
	}

	@Override
	public void delete(Object [] params) {
		QuizTDesc = new MSS_RQ_TableDescriptor(new String[]{"�"}, new Class[]{Integer.class});
		QuizT_XML_Desc = new MSS_RQ_XMLtoTableDescriptor(new String[]{"name","ID"});
		QuizUpdater = new MSS_RQ_CxListFiller(QuizTDesc,QuizT_XML_Desc, ListGroupsElementData.class);
		@SuppressWarnings("unused")
		String xml_arr_ans = MSS_RQ_Request.http_request(reqHandlerAdmin.deleteGroup((Integer)params[0]),UploadFrame.URL);
	}

	@Override
	public void refresh(Object [] params) {
		QuizTDesc = new MSS_RQ_TableDescriptor(new String[]{"�"}, new Class[]{Integer.class});
		QuizT_XML_Desc = new MSS_RQ_XMLtoTableDescriptor(new String[]{"name","ID"});
		QuizUpdater = new MSS_RQ_CxListFiller(QuizTDesc,QuizT_XML_Desc, ListGroupsElementData.class);
		String xml_arr_ans = MSS_RQ_Request.http_request(reqHandlerAdmin.listOwnGroups(),UploadFrame.URL);
		QuizUpdater.updateData(xml_arr_ans);
		QuizUpdater.fillCxList(list);
	}
	
	@Override
	public void edit(Object [] params)
	{
		QuizTDesc = new MSS_RQ_TableDescriptor(new String[]{"�"}, new Class[]{Integer.class});
		QuizT_XML_Desc = new MSS_RQ_XMLtoTableDescriptor(new String[]{"name","ID"});
		QuizUpdater = new MSS_RQ_CxListFiller(QuizTDesc,QuizT_XML_Desc, ListGroupsElementData.class);
		@SuppressWarnings("unused")
		String xml_arr_ans = MSS_RQ_Request.http_request(reqHandlerAdmin.editGroup(Integer.parseInt((String)params[0]),(String)params[1] ),UploadFrame.URL);
		//QuizUpdater.updateData(xml_arr_ans);
		//QuizUpdater.fillCxList(list);
	}
	public void saveDATA(int idUser, String pswd,int idTask,String data)
	{
		MSS_RQ_Admin adm = new MSS_RQ_Admin("SARSUploader", String.valueOf(idUser), pswd);
		MSS_RQ_Request.http_request(adm.sitTask(idTask,data),UploadFrame.URL);
	}
	@Override
	public void refreshHeirGroups(Object[] params) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void refreshGroupMembers(Object[] params) {
		// TODO Auto-generated method stub
		
	}
}
