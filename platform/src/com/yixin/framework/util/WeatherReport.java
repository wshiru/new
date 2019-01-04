package com.yixin.framework.util;

import java.io.BufferedReader;
import java.io.InputStream;    
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.URL;    
import java.net.URLConnection;    
import javax.xml.parsers.DocumentBuilder;    
import javax.xml.parsers.DocumentBuilderFactory;    
import org.w3c.dom.Document;    
import org.w3c.dom.Node;    
import org.w3c.dom.NodeList;    


/* 气象数据格式：

 * 0:广东
1:深圳
2:59493
3:59493.jpg
4:2010-6-17 13:43:01
5:28℃/31℃

6:6月17日 多云
7:无持续风向微风

8:1.gif
9:1.gif
10:今日天气实况：气温：30℃；风向/风力：西南风 5级；湿度：75%；气压：1000hPa；空气质量：中；紫外线强度：中等
11:
穿衣指数：天气炎热，建议着短衫、短裙、短裤、薄型T恤衫、敞领短袖棉衫等清凉夏季服装。

感冒指数：各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。

晨练指数：早晨气象条件较适宜晨练，注意选择通风凉爽的地点。适量饮水以及时补充体内水分。

洗车指数：适宜洗车，未来持续两天无雨天气较好，适合擦洗汽车，蓝天白云、风和日丽将伴您的车子连日洁净。

晾晒指数：多云，适宜晾晒。赶紧把久未见阳光的衣物搬出来吸收一下太阳的味道吧！
旅游指数：多云，有时云会遮挡阳光，但丝毫不会影响您的心情。微风，虽天气稍热，却仍适宜旅游，不要错过机会呦！

路况指数：晴天，条件适宜，路面比较干燥，路况较好。

舒适度指数：白天天气晴好，并且空气湿度偏大，在这种天气条件下，您会感到有些闷热，不很舒适。

12:27℃/32℃

13:6月18日 多云
14:无持续风向微风

15:1.gif
16:1.gif
17:27℃/32℃

18:6月19日 多云
19:无持续风向微风

20:1.gif
21:1.gif
22:深圳市位于广东省中南沿海地区，珠江入海口之东偏北。深圳市地处中华人民共和国广东省中南沿海，陆域位置东经113°46′至114°37′，北纬22°27′至22°52′。东西长81.4公里，南北宽（最短


处）为10.8公里，东临大鹏湾，西连珠江口，南邻香港，与九龙半岛接壤，与香港新界一河之隔，被称为“香港的后花园”。深圳这座新兴的城市整洁美丽，四季草木葱笼，当地政府因地制宜地开发了不


少旅游景点，将自然风光与人工建筑巧妙结合。深圳历史悠久，文化发达，旅游资源也十分丰富，保存在地上、地下的文物古迹十分丰富。80年代深圳博物馆考古人员进行了文物普查，发现了一大批颇有

价值的古建筑、古遗址、古墓葬、古寺庙、古城址和风景名胜等。深圳市人民政府于1983年先后公布了两批重点文物保护单位，并对名胜古迹作了修复，再现了原有的风貌，以供游人观赏。深圳地处北回


归线以南,属亚热带海洋性气候,气候温和,雨量充沛,日照时间长。夏无酷暑,时间长达6个月。春秋冬三季气候温暖,无寒冷之忧。年平均气温为22.3℃。景观：锦绣中华、世界之窗、明思克航母世界、欢乐


谷

*/

/**   
 * 天气预报数据接口类

 * 功能描述：可按日期取出三天内的天气详细情况

 *     
 * @author 梁立全   
 *   
*/ 

public class WeatherReport implements Serializable  {    
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6184237857596079059L;
   
	private String cityName="深圳";         /*先根据IP地址获取城市名，获取不到时默认为深圳*/
	private String cityInfo ;              //城市信息
	
	//今天气象
	private String today_low;              //最低气温

    private String today_high;             //最高气温

    private String today_weather;          //气象
    private String today_weather_png1;      //天气图片1
    private String today_weather_png2;      //天气图片2
    private String today_winddirection;    //风向
    private String today_weather_detail;   //天气实况
    private String today_weather_index;    //天气指数
     
    //明天气象
    private String tomorrow_low;            //最低气温

    private String tomorrow_high;           //最高气温

    private String tomorrow_weather;        //气象
    private String tomorrow_weather_png1;    //天气图片1
    private String tomorrow_weather_png2;    //天气图片2
    private String tomorrow_winddirection;  //风向 
    
    //后天天气
    private String dayAfterTomorrow_low;      //最低气温

    private String dayAfterTomorrow_high;     //最高气温

    private String dayAfterTomorrow_weather;  //气象
    private String dayAfterTomorrow_png1;     //天气图片1
    private String dayAfterTomorrow_png2;     //天气图片2
    private String dayAfterTomorrow_winddirection;   //风向
    
    
    /**
     * 根据外网IP地址获取城市名

     * @return
     */
    public String  getIPCityName(){
    	  try {
    	  	 String  strUrl ="http://fw.qq.com/ipaddress";
    	  	 URL url = new URL(strUrl);
    	  	 BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
    	  	 
    	  	 String s = "";
    	  	 StringBuffer sb = new StringBuffer("");
    	  	 while ((s = br.readLine()) != null) {
    	  	       sb.append(s + "\r\n");
    	  	 }
    	  	 br.close();
    	  	 
    	  	 String s1  = sb.toString();
    	  	 int start = s1.indexOf("(") + 1;
    	  	 int end = s1.indexOf(")");
   	  	     String  s2 = s1.substring(start,end);
   	  	     String[] values = s2.split(","); 
   	  	     String[] webcons = values[values.length-1].split("市");
   	  	     String  ret =  webcons[0].substring(1);
   	  	  	   
    	  	 return ret;

    	  } catch (Exception e) {
     	  	   return null;  	    
    	   }
    }
        
   
    /**
    * 获取SOAP的请求头，并替换其中的标志符号为用户输入的城市

    * 
    * @param city 用户输入的城市名称

    *            
    * @return 客户将要发送给服务器的SOAP请求
    */
    private  String getSoapRequest(String city) {
       StringBuilder sb = new StringBuilder();
       sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>"
           + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
           + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
           + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
           + "<soap:Body>    <getWeatherbyCityName xmlns=\"http://WebXml.com.cn/\">"
           + "<theCityName>" + city
           + "</theCityName>    </getWeatherbyCityName>"
           + "</soap:Body></soap:Envelope>");
       return sb.toString();
    }


    /**
    * 用户把SOAP请求发送给服务器端，并返回服务器点返回的输入流
    * 
    * @param city用户输入的城市名称

    * @return 服务器端返回的输入流，供客户端读取

    * @throws Exception
    */
    private  InputStream getSoapInputStream(String city) throws Exception {
       try {
          String soap = getSoapRequest(city);
          if (soap == null) {
             return null;
          }
          URL url = new URL(
             "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx");
          URLConnection conn = url.openConnection();
          conn.setUseCaches(false);
          conn.setDoInput(true);
          conn.setDoOutput(true);

          conn.setRequestProperty("Content-Length", Integer.toString(soap
          .length()));
          conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
          conn.setRequestProperty("SOAPAction",
          "http://WebXml.com.cn/getWeatherbyCityName");

          OutputStream os = conn.getOutputStream();
          OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
          osw.write(soap);
          osw.flush();
          osw.close();

        InputStream is = conn.getInputStream();
        return is;
       } catch (Exception e) {
    	    //e.printStackTrace();
            return null;
       }
    }

    /**
    * 对服务器端返回的XML进行解析
    * 
    * @param city用户输入的城市名称

    * @return 字符串 用,分割
    */
    public  String getWeather(String city) {
       try {
          Document doc;
          DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
          dbf.setNamespaceAware(true);
          DocumentBuilder db = dbf.newDocumentBuilder();
          InputStream is = getSoapInputStream(city);
          doc = db.parse(is);
          NodeList nl = doc.getElementsByTagName("string");
          StringBuffer sb = new StringBuffer();
          for (int count = 0; count < nl.getLength(); count++) {
             Node n = nl.item(count);
             if (n.getFirstChild().getNodeValue().equals("查询结果为空！")) {
             sb = new StringBuffer("#");
             break;
         }
         sb.append(n.getFirstChild().getNodeValue() + "#\n");
        }
        is.close();
        return sb.toString();
       } catch (Exception e) {
          // e.printStackTrace();
           return null; 
       }
    }


    public  boolean getTodayeWeather() {
    	
       String name = this.getIPCityName();
 	   
       if ( name != null  &&  !"\"".equals(name.trim()) &&  !"".equals(name.trim()) ) {
 	       this.cityName = name;
 	   }
 	    
       long startMili = System.currentTimeMillis();
       
       StringBuffer sb = new StringBuffer();
       try {
         Document doc;
         DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         dbf.setNamespaceAware(true);
         DocumentBuilder db = dbf.newDocumentBuilder();
         InputStream is = getSoapInputStream(this.cityName);
       
         doc = db.parse(is);
         NodeList nl = doc.getElementsByTagName("string");
         for (int count = 0; count < nl.getLength(); count++) {
           Node n = nl.item(count);
           if (n.getFirstChild().getNodeValue().equals("查询结果为空！")) {
              sb = new StringBuffer("#");
              break;
           }
           sb.append(n.getFirstChild().getNodeValue() + "#\n");   
         }
         is.close();
        
         if ( sb.toString() !="#" ){
        	        
           String wearths = sb.toString();
           String[] weath = wearths.split("#");
           
            //以下代码可输出未来三天内的所有天气预报信息数据 调试可用
           /*StringBuffer fd = new StringBuffer();
          
           for (int i = 0; i < weath.length; i++) {	   
       	         System.out.println(i + ":" + weath[i].trim());
         	     if(i != 0 &&  i != 2 &&  i != 3  &&  i != 4 &&  i != 8 
        		        &&  i != 9 &&  i != 15 &&  i != 16 &&  i != 20 &&  i != 21  &&  i != 23  ){ 
                    fd.append(weath[i].trim());
        	      }
            }
           */ 
           
           
           //城市信息
           this.cityInfo = weath[22];
           
           //今天天气
           String thisweath = weath[6]; //气象
           String[] thisweat = thisweath.split(" ");
           String  thisTemperature = weath[5];//气温
           String[] temperature  = thisTemperature.split("/");    
      
           this.today_weather = thisweat[1];
           this.today_low = temperature[0];
           this.today_high =temperature[1];	
           this.today_winddirection  = weath[7];
           this.today_weather_detail = weath[10];
           this.today_weather_index = weath[11];
           this.today_weather_png1 = weath[8];
           this.today_weather_png2 = weath[9];
                
           //明天天气
           thisweath = weath[13]; //气象
           thisweat = thisweath.split(" ");
           thisTemperature = weath[12];//气温
           temperature  = thisTemperature.split("/");  
           this.tomorrow_weather = thisweat[1];  
           this.tomorrow_low = temperature[0];
           this.tomorrow_high = temperature[1];
           this.tomorrow_winddirection  = weath[14];
           this.tomorrow_weather_png1 =weath[15];
           this.tomorrow_weather_png2 =weath[16];
                  
           //后天天气
           thisweath = weath[18]; //气象
           thisweat = thisweath.split(" ");
           thisTemperature = weath[17];//气温
           temperature  = thisTemperature.split("/");  
           this.dayAfterTomorrow_weather = thisweat[1];
           this.dayAfterTomorrow_low = temperature[0];
           this.dayAfterTomorrow_high = temperature[1];
           this.dayAfterTomorrow_winddirection = weath[19];
           this.dayAfterTomorrow_png1 = weath[20];
           this.dayAfterTomorrow_png2 = weath[21];
           
           
         }
         
       } catch (Exception e) {
           return false;
       }
	   
       return true;
          
    }

    
	public String getToday_weather() {
		return today_weather;
	}


	public void setToday_weather(String today_weather) {
		this.today_weather = today_weather;
	}


	public String getTomorrow_weather() {
		return tomorrow_weather;
	}


	public void setTomorrow_weather(String tomorrow_weather) {
		this.tomorrow_weather = tomorrow_weather;
	}


	public String getToday_low() {
		return today_low;
	}


	public void setToday_low(String today_low) {
		this.today_low = today_low;
	}


	public String getToday_high() {
		return today_high;
	}


	public void setToday_high(String today_high) {
		this.today_high = today_high;
	}


	public String getTomorrow_low() {
		return tomorrow_low;
	}


	public void setTomorrow_low(String tomorrow_low) {
		this.tomorrow_low = tomorrow_low;
	}


	public String getTomorrow_high() {
		return tomorrow_high;
	}


	public void setTomorrow_high(String tomorrow_high) {
		this.tomorrow_high = tomorrow_high;
	}


	public String getToday_weather_png() {
		return today_weather_png1;
	}


	public void setToday_weather_png(String today_weather_png) {
		this.today_weather_png1 = today_weather_png;
	}




	public String getToday_winddirection() {
		return today_winddirection;
	}


	public void setToday_winddirection(String today_winddirection) {
		this.today_winddirection = today_winddirection;
	}


	public String getTomorrow_winddirection() {
		return tomorrow_winddirection;
	}


	public void setTomorrow_winddirection(String tomorrow_winddirection) {
		this.tomorrow_winddirection = tomorrow_winddirection;
	}


	public String getDayAfterTomorrow_low() {
		return dayAfterTomorrow_low;
	}


	public void setDayAfterTomorrow_low(String dayAfterTomorrow_low) {
		this.dayAfterTomorrow_low = dayAfterTomorrow_low;
	}


	public String getDayAfterTomorrow_high() {
		return dayAfterTomorrow_high;
	}


	public void setDayAfterTomorrow_high(String dayAfterTomorrow_high) {
		this.dayAfterTomorrow_high = dayAfterTomorrow_high;
	}


	public String getDayAfterTomorrow_weather() {
		return dayAfterTomorrow_weather;
	}


	public void setDayAfterTomorrow_weather(String dayAfterTomorrow_weather) {
		this.dayAfterTomorrow_weather = dayAfterTomorrow_weather;
	}


	public String getToday_weather_png1() {
		return today_weather_png1;
	}


	public void setToday_weather_png1(String today_weather_png1) {
		this.today_weather_png1 = today_weather_png1;
	}


	public String getToday_weather_png2() {
		return today_weather_png2;
	}


	public void setToday_weather_png2(String today_weather_png2) {
		this.today_weather_png2 = today_weather_png2;
	}


	public String getTomorrow_weather_png1() {
		return tomorrow_weather_png1;
	}


	public void setTomorrow_weather_png1(String tomorrow_weather_png1) {
		this.tomorrow_weather_png1 = tomorrow_weather_png1;
	}


	public String getTomorrow_weather_png2() {
		return tomorrow_weather_png2;
	}


	public void setTomorrow_weather_png2(String tomorrow_weather_png2) {
		this.tomorrow_weather_png2 = tomorrow_weather_png2;
	}


	public String getDayAfterTomorrow_png1() {
		return dayAfterTomorrow_png1;
	}


	public void setDayAfterTomorrow_png1(String dayAfterTomorrow_png1) {
		this.dayAfterTomorrow_png1 = dayAfterTomorrow_png1;
	}


	public String getDayAfterTomorrow_png2() {
		return dayAfterTomorrow_png2;
	}


	public void setDayAfterTomorrow_png2(String dayAfterTomorrow_png2) {
		this.dayAfterTomorrow_png2 = dayAfterTomorrow_png2;
	}


	public String getDayAfterTomorrow_winddirection() {
		return dayAfterTomorrow_winddirection;
	}


	public void setDayAfterTomorrow_winddirection(
			String dayAfterTomorrow_winddirection) {
		this.dayAfterTomorrow_winddirection = dayAfterTomorrow_winddirection;
	}


	public String getCityInfo() {
		return cityInfo;
	}


	public void setCityInfo(String cityInfo) {
		this.cityInfo = cityInfo;
	}


	public String getToday_weather_detail() {
		return today_weather_detail;
	}


	public void setToday_weather_detail(String today_weather_detail) {
		this.today_weather_detail = today_weather_detail;
	}


	public String getToday_weather_index() {
		return today_weather_index;
	}


	public void setToday_weather_index(String today_weather_index) {
		this.today_weather_index = today_weather_index;
	}


	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

    
}  
