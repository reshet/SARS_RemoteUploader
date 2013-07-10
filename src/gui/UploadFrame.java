package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.lowagie.text.pdf.codec.Base64;


import base_connectivity.MSS_Pair;
import base_connectivity.MSS_RQ_CxListFiller;
import base_connectivity.MSS_RQ_TableDescriptor;
import base_connectivity.MSS_RQ_XML;
import base_connectivity.MSS_RQ_XML_Pattern;
import base_connectivity.MSS_RQ_XMLtoTableDescriptor;
import base_connectivity.SP_ServerDispatcher;

public class UploadFrame extends JFrame{
	/**
	 * 
	 */
	public static String URL = "http://54.225.84.44/MSS/MainGate.php";
	public static String login = "10000";
	public static String pswd = "mysecret";
	private static final long serialVersionUID = 2014452938136149324L;
	private JTextField login_edit;
	private JButton send_btn = new JButton("Сканировать флешки и отправить");
	private JTextArea status_lbl = new JTextArea("сканирование еще не произведено");
	public UploadFrame(){
		super();
		setSize(600, 420);
		setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int)(screenSize.getWidth()/2-this.getWidth()/2), (int)(screenSize.getHeight()/2-this.getHeight()/2));
		this.setTitle("SARS: Выгрузить интервью на сервер");
		this.setLayout(new BorderLayout(5, 5));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//NumberFormat f = NumberFormat.getNumberInstance(); 
		//f.setMaximumIntegerDigits(10);
		login_edit = new JTextField(10);
		status_lbl.setColumns(50);
		status_lbl.setWrapStyleWord(true);
		status_lbl.setRows(20);
		//login_edit.setColumns(10);
		JPanel mp = new JPanel(new BorderLayout(5,5));
		JPanel sp = new JPanel();
		//sp.set
		
		JPanel p = new JPanel();
		//this.setLayout(L)
		p.add(new Label("Ваш логин:"));
		//login_edit.
		p.add(login_edit);
		p.add(send_btn);
		sp.add(new Label("Статус"));
		//JScrollPane scr = new JScrollPane();
		
		sp.add(new JScrollPane(status_lbl));
		
		mp.add(p,BorderLayout.NORTH);
		mp.add(sp,BorderLayout.CENTER);
		this.add(mp);
		
		
		send_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				status_lbl.setText("Сканирование начато, логин "+login_edit.getText()+"\n");
				new Thread(new Runnable() {
					@Override
					public void run() {
						scan_all_flashes();
					}
				}).start();
				
			}
		});
	}
	private void scan_all_flashes(){
		
		int total_ints = 0;
		int total_ankets = 0;
		int [] ank_ints_counts = new int[100];
		int [] ank_ids = new int[100];
		
		File [] roots = {};
		if(OSValidator.isWindows())roots = File.listRoots();
		else if(OSValidator.isUnix())roots = new File("/media").listFiles();
		else if (OSValidator.isMac())roots = new File("/Volumes").listFiles();
		
		File [][] xml_files = new File[100][1000];
			 //int f_count = 0;	
			 String userID = login_edit.getText(), userPSWD="default";
			 for(File root:roots){
				//System.out.println(root.getAbsolutePath()); 
				if (new File(root.getAbsolutePath()+"/msmart.flag").exists())
				{
//					StringBuilder strs = new StringBuilder();
//					Scanner scns = null;
//					try {
//						scns = new Scanner(new File(root.getAbsolutePath()+"/msmart.flag"));
//						while(scns.hasNextLine()) {
//							strs.append(scns.nextLine());
//						}
//					} catch (FileNotFoundException e) {
//						e.printStackTrace();
//					}
//					String settings = strs.toString();
//					
//					MSS_RQ_TableDescriptor NewsTDesc = new MSS_RQ_TableDescriptor(new String[]{"USERID","PSWD"},
//							new Class[]{String.class,String.class});
//				    MSS_RQ_XMLtoTableDescriptor NewsT_XML_Desc = new MSS_RQ_XMLtoTableDescriptor(new String[]{"USERID","PSWD"});
//				    MSS_RQ_CxListFiller NewsUpdater = new MSS_RQ_CxListFiller(NewsTDesc,NewsT_XML_Desc,NewsServiceElementData.class);		   
//				    userID = NewsUpdater.requestLocalData_FromRoot(settings, 0);
//				    userPSWD = NewsUpdater.requestLocalData_FromRoot(settings, 1);
//				    
					
					//here find all saved interiews in all ankets 
					File dir = new File(root.getAbsolutePath()+"/capital/");
					if (dir.isDirectory())
					{
						File [] subdirs = dir.listFiles();
						for (File d: subdirs)
						{	
							if(d.isDirectory()){
								String anket_id = d.getName();
								int ank_id = Integer.parseInt(anket_id);
								status_lbl.append("Найдена папка анкеты "+ank_id+"\n");
								File [] files = d.listFiles();
								int total_ank_ints = 0;
								ank_ids[total_ankets] = ank_id;
								for (File f: files)
								{	
									if (f.getAbsolutePath().endsWith(".xml"))
									{
										xml_files[total_ankets][total_ank_ints++] = f;
										total_ints++;
									}
								}
								//status_lbl.i
								status_lbl.append("Обнаружено "+total_ank_ints+" интервью  для анкеты "+ank_id+"\n");
								ank_ints_counts[total_ankets] = total_ank_ints;
								total_ankets++;
							}
						}
					}
				}
			 }
			 
			 status_lbl.append("Всего обнаружено "+total_ints+" интервью. Чтение с карты...\n");
			 JList panel_list = new JList();
				
				panel_list.setBackground(new Color(50,60,70));
				panel_list.setSelectionBackground(new Color(69,149,38));
				panel_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				panel_list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
				panel_list.setVisibleRowCount(-1);
				//SP_CellRenderer renderer = new SP_CellRenderer();
				//panel_list.setCellRenderer(renderer);
				
				SP_ServerDispatcher servDisp = new SP_ServerDispatcher(panel_list);
			// servDisp;
			
			for (int i = 0; i < total_ankets;i++)
			{
				for (int j = 0; j < ank_ints_counts[i];j++)
				{
					System.out.println(xml_files[i][j].getAbsolutePath());
					StringBuilder str = new StringBuilder();
					try {
						BufferedReader input =  new BufferedReader(new FileReader(xml_files[i][j]));
					      try {
					        String line = null; 
					        while (( line = input.readLine()) != null){
					          str.append(line);
					          str.append(System.getProperty("line.separator"));
					        }
					      } 
					      finally {
						        input.close();
						  }
					}
				      catch (IOException e) {
						e.printStackTrace();
					}
					ArrayList<MSS_RQ_XML_Pattern> ptn;
					
					try {
						String interview = str.toString();
						
						if(OSValidator.isUnix()) interview = new String(str.toString().getBytes(),"cp1251");
						else if(OSValidator.isWindows())interview = new String(str.toString().getBytes(),"UTF-8");
						
						//interview = str.toString();
						//String ans = Base64.encodeBytes(interview.getBytes("CP1251"));
						String ans = Base64.encodeBytes(interview.getBytes());
						status_lbl.append("Отправка "+(j+1)+" интервью "+ank_ids[i]+" анкеты ...\n");
						servDisp.saveDATA(Integer.parseInt(userID),userPSWD,ank_ids[i],ans);
						status_lbl.append("Отправлено.\n");
						
						System.out.println(interview);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			status_lbl.append("Отправка интервью на сервер завершена.\n");
	}
}
