package com.propolis.levels;

public  class LevelMessages {
	/* 
	 * total hack - kids - don't try this at home
	 */
	private boolean firstMessage = false;
	private boolean secondMessage = false;
	private boolean thirdMessage = false;
	private boolean fourthMessage = false;
	private boolean fifthMessage = false;
	private boolean sixthMessage = false;
	private boolean seventhMessage = false;
	private boolean eigthMessge = false;
	private boolean ninethMessage = false;
	private boolean tenthMessage = false;
	
	
	public LevelMessages(){
		
	}
	public String showMessage(){

		// get where we are
		// show the appropriate message
		if (firstMessage == false){
			firstMessage = true;
			return "Bee: Julia! Looks like this is your lucky day! \nI just got a message from up top, " +
					"the Queen \nherself wants to talk to you. Your days of \nfeeding larvae are over. " +
					"\n\nYou best make your way down to the \nRoyal COMB right away!" +
					"\n\nAh, you might want to keep your knife handy." +
					"\nThere’s a rumor going around that \nthere’s a FUNGUS AMUNGUS!" +
					"\n\n(Press Escape)";
		}
		if (secondMessage == false){
			secondMessage = true;
			return "Guard: Halt! You’re not on feeding duty.  \nWhat is your business here?" +
					"\nJ- Julia!" +
					"\nGuard: What?" +
					"\nJulia: My name is Julia and \nI was summoned her by her Majesty." +
					"\nGuard: Tsk, tsk. Fine." +
					"\nShe’s letting in all kinds nowadays.  \nWell, go on in." +
					"\n\n(Press Escape)";
		}
		if (thirdMessage == false){
			thirdMessage = true;
			return "Queen: Enter my child.\n......" +
					"\nAh! Now I remember. Number 2000 isn’t it?" +
					"\nJulia: Ummm" +
					"\nQueen: 2 has always been my lucky number. And then \nthere’s all those lovely zeros!" +
					"\nBut I didn’t call you here to talk about numbers. " +
					"\nThere is something even more pressing to discuss \nif you can believe that! " +
					"\nA monster has infected our hive. \nA silent, but deadly, killer that warps the" +
					"\nmind and body to it’s will. " +
					"\nThere is....a FUNGUS AMUNGUS!";
		}
		if (fourthMessage == false){
			fourthMessage = true;
			return "....." +
					"\nQueen: I’m sure you’ve already seen what this terrible " +
					"\nmold can do.  We can fight off the infected but" +
					"\n the fungus will still remain.  There is only one" +
					"\nchoice.   You much gather the resin of the conifer. " +
					"\nThis resin is a powerful anti-fungus and with " +
					"\nit we will eradicate this terrible scurge. " +
					"\n\nGo, my child, and collect this resin." +
					"\n\nJulia: Yes, your majesty";
		}
		if (fifthMessage == false){
			fifthMessage = true;
			return "Guard: The Queen sure does love to talk, \ndoesn’t she?" +
					"\nJulia: Well...\nGuard: It was rhetorical, don’t answer. " +
					"\nAnyway you best get going. Just keep " +
					"\nheading down and you’ll get to the exit." +
					"\n\nJulia: Ummm, thanks." +
					"\n\nGuard:  Mmmmh.";
		}

		return "";
		
	}

}
