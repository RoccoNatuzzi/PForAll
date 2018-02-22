package bot.main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@SpringBootApplication
public class Application {

	public static URL finalulr;

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
		ApiContextInitializer.init();
		TelegramBotsApi botsApi = new TelegramBotsApi();
		try {
			botsApi.registerBot(new MyBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public static void initImage() {
		// The url of the website. This is just an example
		ArrayList<String> site = new ArrayList<String>();
		site.add("http://fuckmeharder-please.tumblr.com/");// 0
		site.add("https://finegirlsdailycumshots.tumblr.com/");
		site.add("http://girlofpb.tumblr.com/");// 2

		int random = (int) (Math.random() * 3);
		// System.out.println(random);
		String webSiteURL = site.get(random);

		try {

			// Connect to the website and get the html
			Document doc = Jsoup.connect(webSiteURL).get();

			// Get all elements with img tag ,
			Elements img = doc.getElementsByTag("img");
			int cont = (int) (Math.random() * 10);
			// System.out.println(cont+"-------------");
			int i = 0;
			for (Element el : img) {
				if (cont == i) {
					// for each element get the srs url
					String src = el.absUrl("src");

					System.out.println("Image Found!");
					System.out.println("src attribute is : " + src);

					getImages(src);
					break;
				} else {
					i++;
				}

			}

		} catch (IOException ex) {
			System.err.println("There was an error");
		}
	}

	private static void getImages(String src) throws IOException {
		// String folderPath = "/C:/Users/rocco/Desktop/Nuova";

		// String folder = null;
		// Exctract the name of the image from the src attribute
		int indexname = src.lastIndexOf("/");

		if (indexname == src.length()) {
			src = src.substring(1, indexname);
		}

		indexname = src.lastIndexOf("/");
		String name = src.substring(indexname, src.length());

		// System.out.println(name);

		// Open a URL Stream
		URL url = new URL(src);
		finalulr = url;
		if ((!finalulr.toString().endsWith("jpg") && !finalulr.toString().endsWith("gif"))
				|| finalulr.toString().endsWith("logo.jpg")) {
			initImage();
		}
		/*
		 * InputStream in = url.openStream(); OutputStream out = new
		 * BufferedOutputStream(new FileOutputStream(folderPath + name)); for (int b; (b
		 * = in.read()) != -1; ) { out.write(b); } out.close(); in.close();
		 */
	}

}
