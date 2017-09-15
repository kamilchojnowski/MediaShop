package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeConroller extends SessionedController {
	
	@GetMapping({"", "/home"})
	public String home(Model m){
			if(session().getAttribute("user")!=null) {
				m.addAttribute("msg","zalogowano");
			}
		return "home";
	}
	
	
	
	
	
	
//	
//	@Autowired
//	private ProductRepository productRepository;
//	@Autowired
//	private CategoryRepository categoryRepository;
//	@Autowired
//	private SubcategoryRepository subcategoryRepository;
	
	@GetMapping("/create-content")
	@ResponseBody
	public String createContent() {
//
//		Category category1 = new Category("RTV i Telewizory");
//		Category category2 = new Category("Konsole i Gry");
//		Category category3 = new Category("Smartfony i Telefony");
//		categoryRepository.save(category1);
//		categoryRepository.save(category2);
//		categoryRepository.save(category3);
//		
//		Subcategory subcategory1 = new Subcategory("Telewizory", category1);
//		Subcategory subcategory2 = new Subcategory("Kino domowe", category1);
//		Subcategory subcategory3 = new Subcategory("Słuchawki audio", category1);
//		subcategoryRepository.save(subcategory1);
//		subcategoryRepository.save(subcategory2);
//		subcategoryRepository.save(subcategory3);
//		Subcategory subcategory4 = new Subcategory("Konsole", category2);
//		Subcategory subcategory5 = new Subcategory("Gry", category2);
//		subcategoryRepository.save(subcategory4);
//		subcategoryRepository.save(subcategory5);
//		Subcategory subcategory6 = new Subcategory("Smartfony", category3);
//		Subcategory subcategory7 = new Subcategory("Telefony komórkowe", category3);
//		Subcategory subcategory8 = new Subcategory("Smartwatches", category3);
//		subcategoryRepository.save(subcategory6);
//		subcategoryRepository.save(subcategory7);
//		subcategoryRepository.save(subcategory8);
//		List<Subcategory> list1 = new ArrayList<Subcategory>();
//		list1.add(subcategory1);
//		List<Subcategory> list2 = new ArrayList<Subcategory>();
//		list1.add(subcategory2);
//		List<Subcategory> list3 = new ArrayList<Subcategory>();
//		list1.add(subcategory3);
//		List<Subcategory> list4 = new ArrayList<Subcategory>();
//		list1.add(subcategory4);
//		List<Subcategory> list5 = new ArrayList<Subcategory>();
//		list1.add(subcategory5);
//		List<Subcategory> list6 = new ArrayList<Subcategory>();
//		list1.add(subcategory6);
//		List<Subcategory> list7 = new ArrayList<Subcategory>();
//		list1.add(subcategory7);
//		List<Subcategory> list8 = new ArrayList<Subcategory>();
//		list1.add(subcategory8);
//
//
//		Product p1 = new Product("Telewizor SAMSUNG UE50MU6102K", "Przekatna 50\", UHD 4k, Smukly design, Gwarancja obrazu w 4k,"
//				+ " Jeden pilot Smart Control, Idealnie doswietlone sceny, Sterowanie smartfonem",
//				4, 2549, 45, 1, list1);
//		Product p2 = new Product("Telewizor SAMSUNG UE32M5672AU", "Przekatna 32\", Full High Definition, Technologia PurColour, "
//				+ "Wbudowany tuner DVB-S2, Technologia SmartTV, PVR",
//				5, 1599.99, 39, 1, list1);
//		Product p3 = new Product("Soundbar JBL Cinema SB 450 ", "Subwoofer: Bezprzewodowy, Liczba kanałów: 2.1,"
//				+ " Funkcje Bluetooth: Strumieniowe odtwarzanie muzyki, Możliwość zawieszenia na ścianie: Tak",
//				4.8, 2599, 38, 1, list2);
//		Product p4 = new Product("Słuchawki LOGITECH G430 Surround Sound Gaming Headset", "Min. pasmo przenoszenia [Hz]: 20, "
//				+ "Maks. pasmo przenoszenia [Hz]: 20000, Czułość [dB/mW]: 90, Impedancja [Om]: 32",
//				4.9, 249, 20, 1, list3);
//		Product p5 = new Product("Konsola PlayStation 4 Pro 1TB A Chassis Czarna", "Uklad graficzny: 4.20 TFLOPS AMD Radeon,"
//				+ "Platforma: PlayStation 4 Pro", 5, 1769, 27, 1, list4);
//		Product p6 = new Product("Gra PS4 Uncharted: Zaginione Dziedzictwo", "Gatunek: Akcja , Przygodowa ,"
//				+ "TPP (Third-Person Perspective) , TPS (Third-Person Shooter), Klasyfikacja wiekowa (PEGI):"
//				+ "Przemoc , Wulgaryzmy , 16+, Wersja jezykowa: Polska, Platforma: PlayStation 4",
//				4.1, 149, 60, 1, list5);
//		Product p7 = new Product("Gra PS4 Dishonored 2", "Gatunek: Akcja , FPP (First-Person Perspective) ,"
//				+ "Skradanka, Klasyfikacja wiekowa (PEGI): Przemoc , 18+, Wersja jezykowa: Angielska,"
//				+ "Platforma: PlayStation 4", 4.9, 119, 7, 1, list5);
//		Product p8 = new Product("Smartfon SAMSUNG Galaxy S8 Midnight Black", "Przekatna ekranu [cal]: 5.8, "
//				+ "Rozdzielczosc aparatu [Mpix]: 12, Pamiec wewnetrzna: 64 GB, System operacyjny: Android 7.0 Nougat",
//				5, 3498, 48, 1, list6);
//		Product p9 = new Product("Smartfon SONY Xperia XZ1 Dual Sim Czarny ", "Przekątna ekranu [cal]: 5.2, "
//				+ "Rozdzielczość aparatu [Mpix]: 19, Pamięć wewnętrzna: 64 GB, Pamięć RAM: 4 GB, "
//				+ "System operacyjny: Android 8.0 Oreo", 0, 2999, 32, 1, list6);
//		Product p10 = new Product("Telefon komorkowy NOKIA 3310 Dual SIM Granatowy", "Rodzaj wyswietlacza: Kolorowy , TFT, "
//				+ "Przekatna ekranu [cal]: 2.4, Bluetooth: 3.0, Dual SIM: TAK",
//				3.9, 269, 11, 1, list7);
//		Product p11 = new Product("SmartWatch MANTA SWT9301 Sprita Pro ", "Czujniki: Barometr , G-sensor , "
//				+ "Pedometr (Krokomierz) , Pulsometr , Wysokościomierz, Rozmiar wyświetlacza [cale]: 1.22, "
//				+ "Rozdzielczość wyświetlacza: 240 x 204, Konstrukcja: Klasa ochrony IPX6 , Odporność na wodę",
//				4.4, 299, 7, 1, list8);
//		productRepository.save(p1);
//		productRepository.save(p2);
//		productRepository.save(p3);
//		productRepository.save(p4);
//		productRepository.save(p5);
//		productRepository.save(p6);
//		productRepository.save(p7);
//		productRepository.save(p8);
//		productRepository.save(p9);
//		productRepository.save(p10);
//		productRepository.save(p11);
		return "created - blocked";
	}


}
