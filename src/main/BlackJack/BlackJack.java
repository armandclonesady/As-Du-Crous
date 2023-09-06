package main.BlackJack;

import java.util.Scanner;

import main.App;
import main.Jeu;
import main.Joueur;

public class BlackJack implements Jeu{

    private static final int DUREE = 4;

    public Scanner scanner = new Scanner(System.in);

    
    public User player;
    public User dealer;
    public int bet;
    public CardDeck deck;

    
    public BlackJack(User user, CardDeck deck, User dealer, int bet) {
        this.player = user;
        this.player.setDenomination("joueur");
        this.dealer = dealer;
        this.deck = deck;
        this.bet = bet;
    }

    public BlackJack(User user) {
        this(user, new CardDeck(), new User(), 0);
    }

    public BlackJack() {
        this(new User());
    }

    void bet(int playerBet) {
        this.bet = playerBet;
    }

    void playerDeal() {
        Card deltCard = this.deck.deal();
        this.player.addCard(deltCard);
        this.player.calculateScore();
    }

    void dealerDeal() {
        Card deltCard = this.deck.deal();
        this.dealer.addCard(deltCard);
        if (this.dealer.userHand.size() == 1) {
            deltCard.setVisible(false);   
        }
        this.dealer.calculateScore();
    }    

    

    public void startingDeal() {
        this.playerDeal();
        this.dealerDeal();
        this.playerDeal();
        this.dealerDeal();
        if (this.player.hasBlackJack()) {
            this.victoire();
        }

    }

    void showCards() {
        System.out.println(dealer);
        System.out.println("\n");
        System.out.println(player);
        System.out.println("\n");
    }

    void askForChoice() throws InterruptedException {
        String choice = "";
        while (!(choice.equals("T")) && !(choice.equals("R"))) {
            System.out.println("T- Tirer");
            System.out.println("R- Rester");
            choice = scanner.next().toUpperCase();
        }
        if (choice.equals("T")) {
            this.hit();
        } else if (choice.equals("R")) {
            this.stand();
        }
    }

    void askForBet() {
        int choice = 0;
        while (choice > this.player.currentMoney || choice <= 0) {
            System.out.println("Combien allez-vous risquer?");
            choice = scanner.nextInt();
        }
        bet(choice);
    }

    public void hit() throws InterruptedException {
        this.playerDeal();
        if (this.player.userScore.isBusted()) {
            this.defaite();
        }
        this.showCards();
        this.askForChoice();
    }

    public void stand() throws InterruptedException {
        this.dealer.setAllCardsVisible();
        this.dealer.calculateScore();
        this.showCards();
        do {
            Thread.sleep(1000);
            this.dealerDeal();
            this.showCards();
        } while (this.dealer.getUserScore() < this.player.getUserScore() && !(this.dealer.isBusted()));
        if (this.dealer.isBusted()) {
            this.victoire();
        } else {
            this.defaite();
        }
    }

    void payOut() {
        if (this.player.hasBlackJack()) {
            this.player.setCurrentMoney(this.player.getCurrentMoney()*2 + this.player.getCurrentMoney()/2);    
        }
        this.player.setCurrentMoney(this.player.getCurrentMoney()*2);
    }

    public void startOfGame(Joueur j) throws InterruptedException {
        toUserFromJoueur(j);
        System.out.println("\n" + //
                "\n" + //
                " .----------------.  .----------------.  .----------------.  .----------------.  .----------------. \n" + //
                "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" + //
                "| |   ______     | || |   _____      | || |      __      | || |     ______   | || |  ___  ____   | |\n" + //
                "| |  |_   _ \\    | || |  |_   _|     | || |     /  \\     | || |   .' ___  |  | || | |_  ||_  _|  | |\n" + //
                "| |    | |_) |   | || |    | |       | || |    / /\\ \\    | || |  / .'   \\_|  | || |   | |_/ /    | |\n" + //
                "| |    |  __'.   | || |    | |   _   | || |   / ____ \\   | || |  | |         | || |   |  __'.    | |\n" + //
                "| |   _| |__) |  | || |   _| |__/ |  | || | _/ /    \\ \\_ | || |  \\ `.___.'\\  | || |  _| |  \\ \\_  | |\n" + //
                "| |  |_______/   | || |  |________|  | || ||____|  |____|| || |   `._____.'  | || | |____||____| | |\n" + //
                "| |              | || |              | || |              | || |              | || |              | |\n" + //
                "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" + //
                " '----------------'  '----------------'  '----------------'  '----------------'  '----------------' \n" + //
                "             .----------------.  .----------------.  .----------------.  .----------------.         \n" + //
                "            | .--------------. || .--------------. || .--------------. || .--------------. |        \n" + //
                "            | |     _____    | || |      __      | || |     ______   | || |  ___  ____   | |        \n" + //
                "            | |    |_   _|   | || |     /  \\     | || |   .' ___  |  | || | |_  ||_  _|  | |        \n" + //
                "            | |      | |     | || |    / /\\ \\    | || |  / .'   \\_|  | || |   | |_/ /    | |        \n" + //
                "            | |   _  | |     | || |   / ____ \\   | || |  | |         | || |   |  __'.    | |        \n" + //
                "            | |  | |_' |     | || | _/ /    \\ \\_ | || |  \\ `.___.'\\  | || |  _| |  \\ \\_  | |        \n" + //
                "            | |  `.___.'     | || ||____|  |____|| || |   `._____.'  | || | |____||____| | |        \n" + //
                "            | |              | || |              | || |              | || |              | |        \n" + //
                "            | '--------------' || '--------------' || '--------------' || '--------------' |        \n" + //
                "             '----------------'  '----------------'  '----------------'  '----------------'         \n" + //
                "\n" + //
                "");
        this.deck.shuffle();
        System.out.println("Vous avez "+ this.player.currentMoney+" € dans votre compte banquaire");
        askForBet();
        startingDeal();
        showCards();
        askForChoice();
    }

    void toUserFromJoueur(Joueur j) {
        User user = new User(j.getArgent(), j.getNom());
        this.setPlayer(user);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public User getDealer() {
        return dealer;
    }

    public void setDealer(User dealer) {
        this.dealer = dealer;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public CardDeck getDeck() {
        return deck;
    }

    public void setDeck(CardDeck deck) {
        this.deck = deck;
    }

    @Override
    public void tricher() {
        throw new UnsupportedOperationException("Unimplemented method 'tricher'");
    }

    @Override
    public void jouer() throws InterruptedException {
        this.startOfGame(App.joueur);
    }

    @Override
    public void victoire() {
        this.dealer.setAllCardsVisible();
        this.dealer.calculateScore();
        this.showCards();
        if (this.player.hasBlackJack()) {
            if (this.dealer.hasBlackJack()) {
                System.out.println("Double BlackJack, tu ne gagne rien et ne perds rien");
                return;
            }
        }
        System.out.println("TU AS GAGNER, le dealer a crever");
        scanner.close();
        payOut();
    }

    @Override
    public void defaite() {
        this.dealer.setAllCardsVisible();
        this.dealer.calculateScore();
        this.showCards();
        if (this.dealer.getUserScore() == this.player.getUserScore()) {
            System.out.println("Egalité,\n Tu n'as rien gagné et rien perdu\nAu moins tu n'as pas perdu!");

        }
        if (this.player.isBusted()) {
            System.out.println("Game Over,\n Tu as crever (ton score est passé au dela de 21)\nPas de chance!");
        }
        else {
            System.out.println("Game Over,\nLe dealer a un score plus élevé que le tiens\nPas de chance!");
        }
        scanner.close();
    }

    @Override
    public void baisserTemps() {
        
    }

    @Override
    public int duree() {
        return BlackJack.DUREE;
    }
}
