package com.giotto.web;

import javax.swing.JFrame;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.giotto.visualizer.MappingFrame;

public class App {
    public static void main(String[] args ) throws Exception {		
    	//test
		ResourceConfig config = new ResourceConfig();
		config.packages("com.giotto");
		ServletHolder servlet = new ServletHolder(new ServletContainer(config));
		
		Server server = new Server(2222);
		ServletContextHandler context = new ServletContextHandler(server, "/*");
		context.addServlet(servlet, "/*");
		
//		JFrame frame = new MappingFrame();

//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
//        frame.pack();
//        frame.setVisible(true);
		
		
		try {
		     server.start();
		     server.join();
		 } finally {
		     server.destroy();
		 }
		

    }
}
