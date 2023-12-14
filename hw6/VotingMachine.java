import java.util.Scanner;

public class VotingMachine {
	private static ElectionData electionData = new ElectionData(new MostFirstVotesStrategy());
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			System.out.println(electionData.getCandidates());
			System.out.println(
					"Do you want to [n]ominate someone, [v]ote for someone, change winner [s]trategy, see who [w]on, or [q]uit?");
			String input = scanner.nextLine().toLowerCase();

			if (input.startsWith("q")) {
				break;
			} else if (input.startsWith("n")) {
				System.out.println("Enter a name to nominate:");
				String name = scanner.nextLine();
				try {
					electionData.nominateCandidate(name);
				} catch (Exception e) {
					System.out.println("This name is already on the ballot.");
				}
			} else if (input.startsWith("v")) {
				try {
					System.out.println("Enter your first choice:");
					String firstChoice = scanner.nextLine();
					System.out.println("Enter your second choice:");
					String secondChoice = scanner.nextLine();
					System.out.println("Enter your third choice:");
					String thirdChoice = scanner.nextLine();
					try {
						electionData.submitVote(firstChoice, secondChoice, thirdChoice);
					} catch (MoreThanOnceException e) {
						System.out.println("You have voted for the same candidate more than once. Please vote again.");
					}
				} catch (CandidateNotNominatedException e) {
					System.out.println(
							e.getMessage() + " is not nominated. Would you like to nominate the candidate [y]es/[n]o?");
					String nominate = scanner.nextLine().toLowerCase();
					if (nominate.startsWith("y")) {
						try {
							electionData.nominateCandidate(e.getMessage());
						} catch (Exception ex) {
							System.out.println("Error nominating candidate.");
						}
					}
				}
			} else if (input.startsWith("s")) {
				System.out.println("Which strategy would you like to use? most [f]irst votes or most [a]greeable?");
				String strategy = scanner.nextLine().toLowerCase();
				if (strategy.startsWith("f")) {
					electionData.setStrategy(new MostFirstVotesStrategy());
				} else if (strategy.startsWith("a")) {
					electionData.setStrategy(new MostAgreeableStrategy());
				}
			} else if (input.startsWith("w")) {
				System.out.println("The winner is " + electionData.calculateWinner());
			}
		}
	}
}
