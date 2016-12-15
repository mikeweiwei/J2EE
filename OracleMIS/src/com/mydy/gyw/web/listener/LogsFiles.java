package com.mydy.gyw.web.listener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class LogsFiles implements  ServletRequestListener{


	@Override
	public void requestInitialized(ServletRequestEvent sre) {
			ServletContext context = sre.getServletRequest().getServletContext();
			String path = context.getRealPath("/");
			path = path + "/logs";
			File file = new File(path);
			if( !file.exists()){
				file.mkdir();
			}
			//��ȡ��Ӧ��Ϣ
			HttpServletRequest res = (HttpServletRequest) sre.getServletRequest();
			//��ȡ����ͷ��Ϣ
			StringBuffer url = res.getRequestURL();
			//��ȡ������ʱ��
			Date date = new Date();
			String dateformate = String.format("%1$tF %1$tT", date);
			//��ȡ����ip�Լ��˿ں�
			String addr = res.getRemoteAddr();
			int port = res.getRemotePort();
			
			String FileName = "RequestLogs.txt";
			path = path + "/" + FileName;
			BufferedWriter bw = null;
			try {
				bw =  new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, true)));
				bw.write(dateformate+"#"+addr+":"+port+"#"+url);
				bw.newLine();
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if( bw != null){
					try {
						bw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	}
	
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		
	}

}
