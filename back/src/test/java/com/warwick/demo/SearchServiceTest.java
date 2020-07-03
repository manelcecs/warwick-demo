package com.warwick.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.warwick.domain.SearchRequest;
import com.warwick.domain.SearchResponse;
import com.warwick.service.SearchService;

import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Log4j2
class SearchServiceTest {
	
	@Autowired
	private SearchService searchService;

	@Test
	void searchKeyWordSplitTest1(){
		
		//Keyword to looking for
		String keyWord = "Lorem";
		
		//100 words text
		String text = 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Nunc sed odio vitae ex eleifend maximus eget vel nisi. "
				+ "Phasellus vel pulvinar nisl. "
				+ "Mauris lobortis, velit at porta lacinia, nulla erat luctus urna, "
				+ "vel accumsan purus sapien eget lectus. Suspendisse potenti. "
				+ "In hac habitasse platea dictumst. "
				+ "Suspendisse nec metus vel risus commodo vestibulum eget id ante. "
				+ "In semper lacinia est, quis varius tellus ullamcorper at. "
				+ "Aenean posuere eget erat eget posuere. Nunc suscipit, "
				+ "quam sed vulputate tristique, metus leo sollicitudin leo, "
				+ "vel aliquet eros purus dictum magna. Etiam massa nulla, "
				+ "convallis quis tempus nec, consequat vel erat. "
				+ "Nullam eget finibus orci. ";
		
		log.info("Text case starts");
		log.info("SearchKeyWordSplitTest1");
		
		//Creating an instance of SearchRequest
		SearchRequest request = new SearchRequest();
		
		//Setting keyword
		request.setKeyWord(keyWord);
		//Setting test
		request.setText(text);
		
		SearchResponse response = this.searchService.searchKeyWordSplit(request);
		
		//assert not a null response object
		assertNotNull(response);
		
		log.info("Number of occurrences of {}: {}", keyWord, response.getNumberOccurrences());

		//assert the number of occurrences of 'Lorem' that exactly 1.
		assertEquals(1, response.getNumberOccurrences());
		
		log.info("Time elapsed in method: {} ms \n\n",response.getTimeElapsed());
		
	}
	
	@Test
	void searchKeyWordSplitTest2(){
		
		//Keyword to looking for
		String keyWord = "In";
		
		//1000 words text
		String text = 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse molestie leo est, a tempus lectus accumsan ac. Nunc aliquam rhoncus nisl, et facilisis nunc viverra ac. Ut interdum lacus eu nunc bibendum rhoncus. Fusce feugiat vulputate augue id mollis. Suspendisse ut nulla pellentesque, tempor lacus non, sodales leo. In mauris augue, semper in arcu at, congue porttitor massa. Etiam in rhoncus ipsum, in maximus dolor. Fusce at ante at nisl eleifend convallis vel non dolor." + 
 
				"Donec in mi ac velit congue fringilla at vel eros. Pellentesque eget massa non diam tempus facilisis ac vitae sapien. Quisque nisi ipsum, congue non fermentum sit amet, porttitor at lectus. Nunc ut sodales dolor. Integer hendrerit vestibulum viverra. Integer ut leo in elit tincidunt ultrices. Nullam ornare tellus vitae dolor tempor, consectetur sagittis quam sollicitudin. Nunc viverra ante est. Cras commodo commodo sapien, vitae accumsan sem dictum non. Duis vestibulum aliquet mi ac condimentum. Aliquam in risus in tortor dignissim malesuada. Pellentesque quis mauris in odio pharetra bibendum." + 

				"Phasellus ut ex ut eros rutrum aliquam quis eu lacus. Nunc in blandit libero. Duis maximus felis leo. Aliquam auctor iaculis ligula sed ornare. Suspendisse at sagittis mauris. Maecenas ornare, nisl ornare condimentum tincidunt, eros tortor mollis quam, nec luctus purus sapien tristique libero. Donec nec ipsum tellus. Aliquam a aliquet tortor. Nullam pretium elit sit amet fringilla consequat. Pellentesque a felis sit amet odio malesuada tempor fringilla et ex. Cras imperdiet maximus venenatis. Praesent orci justo, ullamcorper vitae rhoncus fermentum, pellentesque non leo. In aliquam orci nec finibus lacinia." + 

				"Maecenas vitae ante eget tortor gravida iaculis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc vel elit vel nibh interdum egestas. Nunc a pulvinar sapien. Maecenas volutpat at nulla id maximus. Mauris vulputate, felis eu sagittis fermentum, dolor orci pulvinar neque, non luctus arcu arcu sed nunc. Vivamus commodo dapibus erat non laoreet. Curabitur iaculis, lacus ac ornare elementum, tortor nisi mollis quam, id semper metus nibh eget urna. Aliquam ultrices mattis facilisis. Sed vitae eleifend lacus. Aliquam dapibus neque eget erat ornare consequat. Nulla blandit justo magna, eu fringilla dui auctor vel. Integer hendrerit nec tortor aliquam iaculis. Nulla ut aliquam est. Nam rutrum, nulla a finibus varius, est velit gravida sem, non ultricies lectus odio tincidunt nisi." + 

				"Pellentesque facilisis orci id metus aliquet, ac dictum quam tincidunt. Proin sodales nisi vel eleifend semper. Aenean lacinia dictum justo id aliquet. Morbi accumsan in mauris et luctus. Maecenas sit amet finibus augue, quis efficitur sem. Fusce imperdiet facilisis facilisis. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque consectetur elit id volutpat vulputate. Curabitur lacinia semper massa. Ut vel lorem lobortis, posuere ante ac, varius ex. Nam viverra tellus a arcu ornare, nec pretium dolor consectetur. Quisque egestas lorem a nibh malesuada, vel condimentum orci tempor. Ut id mi magna." + 

				"Donec id ligula leo. Nulla ultricies ante eros, in pellentesque orci sodales quis. Donec est ante, fermentum ut tempus in, rhoncus non sapien. Nunc vitae mi laoreet, gravida eros ac, imperdiet augue. Praesent scelerisque facilisis sem ac facilisis. Nunc magna augue, vestibulum egestas posuere consequat, interdum vel nunc. Donec ut dui id mi convallis posuere vel id turpis." + 

				"Aenean tellus quam, ullamcorper ut metus a, imperdiet eleifend nibh. In pellentesque venenatis lacus, at cursus neque tempus eu. Proin facilisis lobortis leo, vitae fringilla enim sagittis ac. Sed ultrices faucibus tellus. Curabitur in efficitur nibh. Vivamus lacinia magna id justo dignissim viverra. Ut turpis enim, tristique id lacus id, convallis sagittis velit." + 

				"Morbi ac placerat ipsum. Aliquam vehicula tincidunt luctus. Sed malesuada, dui hendrerit ullamcorper sodales, nulla diam auctor sem, viverra venenatis sem ante vitae lorem. Maecenas volutpat fermentum est, at congue diam maximus et. Nullam feugiat magna in sapien semper porttitor. Vestibulum neque dolor, rhoncus id erat non, dapibus viverra felis. Donec lectus diam, ullamcorper ut molestie eget, rhoncus quis sem. Nulla tortor dolor, molestie in vehicula efficitur, mattis nec nisl. Nulla auctor porta metus eget pretium. Fusce volutpat sodales dui ut consequat. Integer at sem ac ex eleifend facilisis. Cras vitae justo maximus, sodales justo quis, porttitor dolor. Donec ullamcorper eros sed tellus iaculis semper. Nullam gravida lorem vel tincidunt efficitur." + 

				"Morbi ultricies nulla vel ultrices mollis. Pellentesque eget velit eu sapien venenatis malesuada id et neque. Phasellus nec vehicula erat. Integer ultrices, lacus ultrices egestas consectetur, diam nibh hendrerit augue, venenatis bibendum dui nisl ac massa. Quisque vehicula ligula nisl, id sagittis ex varius vitae. Maecenas posuere sollicitudin ornare. Nam ipsum nulla, ultrices ut ex scelerisque, consequat finibus neque. Morbi viverra quis risus sed finibus. Maecenas vitae scelerisque mauris. Etiam imperdiet malesuada purus, ac malesuada sem dapibus quis. Donec a blandit odio, id lacinia lorem. In et massa orci. Quisque tempus porta nunc sed condimentum. In commodo efficitur leo, eu eleifend metus efficitur eget." + 

				"In neque arcu, rutrum et enim vitae, rutrum consequat orci. Pellentesque nec quam in mauris dictum pellentesque et eget urna. Fusce bibendum, turpis ac auctor dignissim, nibh lacus porttitor ipsum, id mattis eros eros non nulla. Mauris sed sapien aliquet, sodales sapien vitae, accumsan dolor. Morbi vulputate, urna semper tempor lobortis, sem nisi volutpat ex, eget vehicula urna lacus non leo. Sed interdum rhoncus ultrices. Integer quis orci vel risus mollis sagittis id lobortis purus. Nulla finibus magna nec lectus aliquet, et vestibulum odio vestibulum. Suspendisse tincidunt dolor sit amet tortor elementum blandit. Integer luctus mi a congue luctus. Etiam vel tincidunt felis, non venenatis neque. Donec et lobortis neque. Donec mollis purus ut urna placerat, eu tincidunt tortor lacinia. Aenean a dolor dui. Mauris quis interdum sapien." + 

				"Duis ac nisi urna. Vivamus eget maximus elit. Maecenas non placerat ex. Suspendisse lectus ipsum, maximus nec urna faucibus, ultricies suscipit ex. Cras tortor elit, placerat a lacinia id, feugiat nec massa. Aliquam eleifend accumsan magna nec viverra. Morbi vel neque sed turpis interdum sodales. Fusce ultrices dapibus ex, vitae tempus massa finibus vel." + 

				"Pellentesque laoreet ante neque, eget placerat eros aliquam in. Nullam ligula turpis, imperdiet nec tellus nec, ultricies viverra sapien. Fusce nec lacus. ";
		
		log.info("Text case starts");
		log.info("SearchKeyWordSplitTest2");
		
		//Creating an instance of SearchRequest
		SearchRequest request = new SearchRequest();
		
		//Setting keyword
		request.setKeyWord(keyWord);
		//Setting test
		request.setText(text);
		
		SearchResponse response = this.searchService.searchKeyWordSplit(request);
		
		//assert not a null response object
		assertNotNull(response);
		
		log.info("Number of occurrences of {}: {}", keyWord, response.getNumberOccurrences());

		//assert the number of occurrences of 'In' that exactly 23.
		assertEquals(23, response.getNumberOccurrences());
		
		log.info("Time elapsed in method: {} ms \n\n",response.getTimeElapsed());
		
		
	}
	
	@Test
	void searchKeyWordSplitTest3(){
		
		//Keyword to looking for
		String keyWord = "lacus";
		
		//1000 words text
		String text = 
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse molestie leo est, a tempus lectus accumsan ac. Nunc aliquam rhoncus nisl, et facilisis nunc viverra ac. Ut interdum lacus eu nunc bibendum rhoncus. Fusce feugiat vulputate augue id mollis. Suspendisse ut nulla pellentesque, tempor lacus non, sodales leo. In mauris augue, semper in arcu at, congue porttitor massa. Etiam in rhoncus ipsum, in maximus dolor. Fusce at ante at nisl eleifend convallis vel non dolor." + 
 
				"Donec in mi ac velit congue fringilla at vel eros. Pellentesque eget massa non diam tempus facilisis ac vitae sapien. Quisque nisi ipsum, congue non fermentum sit amet, porttitor at lectus. Nunc ut sodales dolor. Integer hendrerit vestibulum viverra. Integer ut leo in elit tincidunt ultrices. Nullam ornare tellus vitae dolor tempor, consectetur sagittis quam sollicitudin. Nunc viverra ante est. Cras commodo commodo sapien, vitae accumsan sem dictum non. Duis vestibulum aliquet mi ac condimentum. Aliquam in risus in tortor dignissim malesuada. Pellentesque quis mauris in odio pharetra bibendum." + 

				"Phasellus ut ex ut eros rutrum aliquam quis eu lacus. Nunc in blandit libero. Duis maximus felis leo. Aliquam auctor iaculis ligula sed ornare. Suspendisse at sagittis mauris. Maecenas ornare, nisl ornare condimentum tincidunt, eros tortor mollis quam, nec luctus purus sapien tristique libero. Donec nec ipsum tellus. Aliquam a aliquet tortor. Nullam pretium elit sit amet fringilla consequat. Pellentesque a felis sit amet odio malesuada tempor fringilla et ex. Cras imperdiet maximus venenatis. Praesent orci justo, ullamcorper vitae rhoncus fermentum, pellentesque non leo. In aliquam orci nec finibus lacinia." + 

				"Maecenas vitae ante eget tortor gravida iaculis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc vel elit vel nibh interdum egestas. Nunc a pulvinar sapien. Maecenas volutpat at nulla id maximus. Mauris vulputate, felis eu sagittis fermentum, dolor orci pulvinar neque, non luctus arcu arcu sed nunc. Vivamus commodo dapibus erat non laoreet. Curabitur iaculis, lacus ac ornare elementum, tortor nisi mollis quam, id semper metus nibh eget urna. Aliquam ultrices mattis facilisis. Sed vitae eleifend lacus. Aliquam dapibus neque eget erat ornare consequat. Nulla blandit justo magna, eu fringilla dui auctor vel. Integer hendrerit nec tortor aliquam iaculis. Nulla ut aliquam est. Nam rutrum, nulla a finibus varius, est velit gravida sem, non ultricies lectus odio tincidunt nisi." + 

				"Pellentesque facilisis orci id metus aliquet, ac dictum quam tincidunt. Proin sodales nisi vel eleifend semper. Aenean lacinia dictum justo id aliquet. Morbi accumsan in mauris et luctus. Maecenas sit amet finibus augue, quis efficitur sem. Fusce imperdiet facilisis facilisis. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque consectetur elit id volutpat vulputate. Curabitur lacinia semper massa. Ut vel lorem lobortis, posuere ante ac, varius ex. Nam viverra tellus a arcu ornare, nec pretium dolor consectetur. Quisque egestas lorem a nibh malesuada, vel condimentum orci tempor. Ut id mi magna." + 

				"Donec id ligula leo. Nulla ultricies ante eros, in pellentesque orci sodales quis. Donec est ante, fermentum ut tempus in, rhoncus non sapien. Nunc vitae mi laoreet, gravida eros ac, imperdiet augue. Praesent scelerisque facilisis sem ac facilisis. Nunc magna augue, vestibulum egestas posuere consequat, interdum vel nunc. Donec ut dui id mi convallis posuere vel id turpis." + 

				"Aenean tellus quam, ullamcorper ut metus a, imperdiet eleifend nibh. In pellentesque venenatis lacus, at cursus neque tempus eu. Proin facilisis lobortis leo, vitae fringilla enim sagittis ac. Sed ultrices faucibus tellus. Curabitur in efficitur nibh. Vivamus lacinia magna id justo dignissim viverra. Ut turpis enim, tristique id lacus id, convallis sagittis velit." + 

				"Morbi ac placerat ipsum. Aliquam vehicula tincidunt luctus. Sed malesuada, dui hendrerit ullamcorper sodales, nulla diam auctor sem, viverra venenatis sem ante vitae lorem. Maecenas volutpat fermentum est, at congue diam maximus et. Nullam feugiat magna in sapien semper porttitor. Vestibulum neque dolor, rhoncus id erat non, dapibus viverra felis. Donec lectus diam, ullamcorper ut molestie eget, rhoncus quis sem. Nulla tortor dolor, molestie in vehicula efficitur, mattis nec nisl. Nulla auctor porta metus eget pretium. Fusce volutpat sodales dui ut consequat. Integer at sem ac ex eleifend facilisis. Cras vitae justo maximus, sodales justo quis, porttitor dolor. Donec ullamcorper eros sed tellus iaculis semper. Nullam gravida lorem vel tincidunt efficitur." + 

				"Morbi ultricies nulla vel ultrices mollis. Pellentesque eget velit eu sapien venenatis malesuada id et neque. Phasellus nec vehicula erat. Integer ultrices, lacus ultrices egestas consectetur, diam nibh hendrerit augue, venenatis bibendum dui nisl ac massa. Quisque vehicula ligula nisl, id sagittis ex varius vitae. Maecenas posuere sollicitudin ornare. Nam ipsum nulla, ultrices ut ex scelerisque, consequat finibus neque. Morbi viverra quis risus sed finibus. Maecenas vitae scelerisque mauris. Etiam imperdiet malesuada purus, ac malesuada sem dapibus quis. Donec a blandit odio, id lacinia lorem. In et massa orci. Quisque tempus porta nunc sed condimentum. In commodo efficitur leo, eu eleifend metus efficitur eget." + 

				"In neque arcu, rutrum et enim vitae, rutrum consequat orci. Pellentesque nec quam in mauris dictum pellentesque et eget urna. Fusce bibendum, turpis ac auctor dignissim, nibh lacus porttitor ipsum, id mattis eros eros non nulla. Mauris sed sapien aliquet, sodales sapien vitae, accumsan dolor. Morbi vulputate, urna semper tempor lobortis, sem nisi volutpat ex, eget vehicula urna lacus non leo. Sed interdum rhoncus ultrices. Integer quis orci vel risus mollis sagittis id lobortis purus. Nulla finibus magna nec lectus aliquet, et vestibulum odio vestibulum. Suspendisse tincidunt dolor sit amet tortor elementum blandit. Integer luctus mi a congue luctus. Etiam vel tincidunt felis, non venenatis neque. Donec et lobortis neque. Donec mollis purus ut urna placerat, eu tincidunt tortor lacinia. Aenean a dolor dui. Mauris quis interdum sapien." + 

				"Duis ac nisi urna. Vivamus eget maximus elit. Maecenas non placerat ex. Suspendisse lectus ipsum, maximus nec urna faucibus, ultricies suscipit ex. Cras tortor elit, placerat a lacinia id, feugiat nec massa. Aliquam eleifend accumsan magna nec viverra. Morbi vel neque sed turpis interdum sodales. Fusce ultrices dapibus ex, vitae tempus massa finibus vel." + 

				"Pellentesque laoreet ante neque, eget placerat eros aliquam in. Nullam ligula turpis, imperdiet nec tellus nec, ultricies viverra sapien. Fusce nec lacus. ";
		
		log.info("Text case starts");
		log.info("SearchKeyWordSplitTest3");
		
		//Creating an instance of SearchRequest
		SearchRequest request = new SearchRequest();
		
		//Setting keyword
		request.setKeyWord(keyWord);
		//Setting test
		request.setText(text);
		
		SearchResponse response = this.searchService.searchKeyWordSplit(request);
		
		//assert not a null response object
		assertNotNull(response);
		
		log.info("Number of occurrences of {}: {}", keyWord, response.getNumberOccurrences());
		
		//assert the number of occurrences of 'lacus' that exactly 11.
		assertEquals(11, response.getNumberOccurrences());
		
		log.info("Time elapsed in method: {} ms \n\n",response.getTimeElapsed());
		
	}	
}
