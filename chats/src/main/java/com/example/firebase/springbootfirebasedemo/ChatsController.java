package com.example.firebase.springbootfirebasedemo;

//import org.springframework.web.bind.annotation.*;

//import java.util.concurrent.ExecutionException;

//import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.ExecutionException;
 
@RestController
@RequestMapping(path="api/")
public class ChatsController {
	
//@Autowired 
//public ChatsController(ChatsService chatsService) {
//    this.chatsService = chatsService;
//}
@Autowired
private ChatsService chatsService;


//public void balabizoTest ()
//{
//	System.out.println("Agmal RRH");
//}


//public String StartNewChat (@RequestBody Chats chat) throws ExecutionException, InterruptedException{


//@PostMapping("Chats")

//@RequestMapping(value = "Chats", method = RequestMethod.POST)

////////////////////////////// Akher testing ma3 Alaa//////////////////////////////////////////////////////////
//@PostMapping(value = "Chats", produces = MediaType.APPLICATION_JSON_VALUE)
//public String StartNewChat(@RequestBody Chats c)
//{ 
//	//chatsService.StartNewChat(c);
//	System.out.println("here ++++++++++++++++============");
//	System.out.println(c);
//	
//	return "Chat created successfully ";
//}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////


//@PostMapping(value = "/Chats", consumes = "application/json", produces = "application/json")
//		public void StartNewChat(@RequestBody Chats c){
////		    response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
////		      .path("/findPerson/" + person.getId()).toUriString());
////
//		     try {
//				chatsService.StartNewChat(c);
//			} catch (ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}


//public Chats StartNewChat (@RequestBody JSONObject body) throws JSONException{
@PostMapping(value = "Chats", consumes = "application/json", produces = "application/json")
public void StartNewChat (@RequestBody Chats c) throws ExecutionException, InterruptedException {
	
//    String t = body.getString("title");
//    int ID = body.getInt("id");
//    String b = body.getString("body");
//	Chats c = new Chats(t, ID, b);S
	try {

		 chatsService.StartNewChat(c);
	} catch (Exception e) {
//		// TODO Auto-generated catch block
//        System.out.println("here");
		System.out.println("error in chatscontroller");
		e.printStackTrace();
	}
//	return c;
//	System.out.println("here is the chat"+ c);
//	return "Success";
}

//Response StartNewChat(@RequestBody Chats c) {
//    System.out.println("StartNewChat" + c.toString());
//    return new Response("Success","200");
//}




//@Value

//public Chats getChatByName(String title) throws ExecutionException, InterruptedException{
//	Chats c1 = new Chats(title, 113, "Ahlan wasahlan");
//	chatsService.getChatByName(title);
//	return c1;
//
//}


	@GetMapping("Chats/{title}")
	public Chats getChatByName (@PathVariable String title)  {

		try {
			return chatsService.getChatByName(title);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	@GetMapping("Chats")
	public List<Chats> getAllChats ()  {

		try {
			return chatsService.getAllChats();
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

    @DeleteMapping("Chats/{title}")
	public String deleteChat(@PathVariable String title){
		try {
			return chatsService.deleteChat(title);
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
