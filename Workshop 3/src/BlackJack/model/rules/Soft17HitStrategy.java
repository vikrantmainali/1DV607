package BlackJack.model.rules;

import BlackJack.model.Card;
import BlackJack.model.Player;

public class Soft17HitStrategy implements IHitStrategy {

	 private final int g_hitLimit = 17;
	
	@Override
	public boolean DoHit(Player a_dealer) 
	{	
		if (a_dealer.CalcScore() == g_hitLimit) 
		{
			for(Card c : a_dealer.GetHand())
			{
				if(c.GetValue() == Card.Value.Ace)
				{
					return true;
				}
			}
		} 
		return a_dealer.CalcScore() < g_hitLimit;       
	}
}
