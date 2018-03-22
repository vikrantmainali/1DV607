package BlackJack.controller;

import BlackJack.view.Choice;
import BlackJack.view.IView;

import java.util.concurrent.TimeUnit;

import BlackJack.model.Game;
import BlackJack.model.ICardDealtObserver;

public class PlayGame implements ICardDealtObserver {
	
	private Game a_game;
	private IView a_view;

	public PlayGame(Game game, IView view)
	{
		a_game = game;
		a_view = view;
		Subscribe(this);
		a_view.DisplayWelcomeMessage();
	}
	
  public boolean Play() 
  {  
   Choice ch = a_view.getChoice();
    
   if(ch == Choice.Play)
   {
	   a_game.NewGame();
   }
   else if(ch == Choice.Hit)
   {
	   a_game.Hit();
   }
   else if(ch == Choice.Stand)
   { 
	   a_game.Stand();
   }
   	return ch != Choice.Quit;
  }
 
  @Override
  public void CardDealt() 
  {
    a_view.DisplayWelcomeMessage();
    a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
    a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());
    
    if(a_game.IsGameOver())
    {
    	a_view.DisplayGameOver(a_game.IsDealerWinner());
    }
    
    try {
		TimeUnit.SECONDS.sleep(2);
	} catch (InterruptedException e) {

		e.printStackTrace();
	}
	}
    
  private void Subscribe(ICardDealtObserver o)
  {
	  this.a_game.AddSubscribers(o);
  }
}