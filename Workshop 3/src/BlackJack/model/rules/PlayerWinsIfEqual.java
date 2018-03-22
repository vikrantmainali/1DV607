package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

public class PlayerWinsIfEqual implements IWinStrategy {

	@Override
	public boolean IsDealerWinner(Dealer a_dealer, Player a_player) {
		if (a_dealer.CalcScore() == a_player.CalcScore())
		{	
			return false;
		}
		else if (a_dealer.CalcScore() > a_player.CalcScore())
		{
			return false;
		}
		else 
			return true;
	}
}
