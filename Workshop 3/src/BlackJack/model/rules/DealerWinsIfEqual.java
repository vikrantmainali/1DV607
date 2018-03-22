package BlackJack.model.rules;

import BlackJack.model.Dealer;
import BlackJack.model.Player;

public class DealerWinsIfEqual implements IWinStrategy{

	@Override
	public boolean IsDealerWinner(Dealer a_dealer, Player a_player) {
		if (a_dealer.CalcScore() == a_player.CalcScore())
		{	
			return true;
		}
		else if (a_dealer.CalcScore() > a_player.CalcScore())
		{
			return true;
		}
		else 
			return false;
	}

}
