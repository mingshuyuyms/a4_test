package Servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import beans.WeatherBean;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Servlet implementation class Parse
 */
@WebServlet("/Parse")
public class Parse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Parse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("cityName");
		PrintWriter out = response.getWriter();
		WeatherBean weatherBean = new WeatherBean();
		ArrayList<String> test= new ArrayList<>();
		URI uri=null;
		try {
			uri = new URIBuilder()
			.setScheme("http")
			.setHost("api.openweathermap.org")
			.setPath("/data/2.5/weather")
			.setParameter("q", name)
			.setParameter("mode", "xml")
			.build();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpClient httpclient=new DefaultHttpClient();;
        HttpGet httpget = new HttpGet(uri);
        HttpResponse httpresponse = httpclient.execute(httpget);
        HttpEntity entity = httpresponse.getEntity();

        
        
        InputStream inputStream= entity.getContent();
        
        
        try {
          DocumentBuilderFactory factory =
            DocumentBuilderFactory.newInstance();
          DocumentBuilder builder = factory.newDocumentBuilder();
          Document document = builder.parse(inputStream);
          Element root = document.getDocumentElement();
          
          
          //out.println(root.getTagName()+" weather");
          
          NodeList cityNodes=root.getElementsByTagName("city");
          for(int i=0;i<cityNodes.getLength();i++){
        	  Element city = (Element)cityNodes.item(i);
        	  weatherBean.setCityName(city.getAttribute("name")); 
        	  
        	  
        	  //out.printf("  City: %s%n",
                     // city.getAttribute("name"));
        	  
          }
          NodeList temperatures=root.getElementsByTagName("temperature");
          for(int i=0;i<temperatures.getLength();i++){
        	  Element temp = (Element)temperatures.item(i);
        	  weatherBean.setTempValue(temp.getAttribute("value"));
        	  weatherBean.setTempMin(temp.getAttribute("min"));
        	  weatherBean.setTempMax( temp.getAttribute("max"));
        	  
        	      
        	  
          }
         
          
        } catch(Exception e) {
          e.printStackTrace();
        }
        test.add("1");
        test.add("2");
        weatherBean.setTest(test);
        
		request.setAttribute("current", weatherBean);
		RequestDispatcher dispatcher =request.getRequestDispatcher("showWeather.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
