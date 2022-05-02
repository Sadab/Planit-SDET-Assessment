package APIcall;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class PetApi {
	public static void main(String[] args) {
		PetApi pet = new PetApi();
		pet.response();
	}

	public void response() {
		try {
			String url = "https://petstore.swagger.io/v2/pet/findByStatus?status=available";
			URL obj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
			connection.setRequestProperty("Accept", "application/xml");

			BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = input.readLine()) != null) {
				response.append(inputLine);
			}
			input.close();
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document document = docBuilder.parse(new InputSource(new StringReader(response.toString())));

			NodeList nodeList = document.getElementsByTagName("Pet");
			System.out.println("Number of pets:" + nodeList.getLength());

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
