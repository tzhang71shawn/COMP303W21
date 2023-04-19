import java.util.*;

/**
 * Represents a sequence of watchables to watch in FIFO order.
 */
public class WatchList implements Bingeable<Watchable> {
	
	private final List<Watchable> aList = new LinkedList<>();
	private String aName;
	private int aNext;
	private Optional<Watchable> aLastWatched;

	private Stack<Command> toUndoCommands;
	private Stack<Command> toRedoCommands;

	
	/**
	 * Creates a new empty watchlist.
	 * 
	 * @param pName
	 *            the name of the list
	 * @pre pName!=null;
	 */
	public WatchList(String pName) {
		assert pName != null;
		aName = pName;
		aNext = 0;

		// no previously watched episode on watchlist creation
		aLastWatched = Optional.empty();

		// no previous actions
		toUndoCommands = new Stack<>();
		toRedoCommands = new Stack<>();
	}
	
	public String getName() {
		return aName;
	}
	
	/**
	 * Changes the name of this watchlist.
	 * 
	 * @param pName
	 *            the new name
	 * @pre pName!=null;
	 */
	public void setName(String pName) {
		assert pName != null;
		Command setNameCommand = createSetNameCommand(pName);
		setNameCommand.execute();
		toUndoCommands.push(setNameCommand);

		// empty commands to redo when executing a command
		toRedoCommands = new Stack<>();
		toRedoCommands.push(setNameCommand);
	}

	private Command createSetNameCommand(String pName) {
		return new Command() {
			final String oldName = aName;
			@Override
			public Optional<Watchable> execute() {
				aName = pName;
				return Optional.empty();
			}

			@Override
			public Optional<Watchable> redo() {
				aName = pName;
				return Optional.empty();
			}

			@Override
			public void undo() {
				aName = oldName;
			}
		};
	}

	public void setLastWatched(Watchable pWatchable) {
		assert pWatchable != null;
		aLastWatched = Optional.of(pWatchable);
	}

	public Optional<Watchable> lastWatched() {
		return aLastWatched;
	}
	
	/**
	 * Adds a watchable at the end of this watchlist.
	 * 
	 * @param pWatchable
	 *            the watchable to add
	 * @pre pWatchable!=null;
	 */
	public void addWatchable(Watchable pWatchable) {
		assert pWatchable != null;

		Command addWatchableCommand = createAddWatchableCommand(pWatchable, this);
		addWatchableCommand.execute();
		toUndoCommands.push(addWatchableCommand);

		// empty commands to redo when executing a command
		toRedoCommands = new Stack<>();
		toRedoCommands.push(addWatchableCommand);
	}

	private Command createAddWatchableCommand(Watchable pWatchable, WatchList pWatchList) {
		return new Command() {
			@Override
			public Optional<Watchable> execute() {
				pWatchable.setWatchList(pWatchList);
				aList.add(pWatchable);
				return Optional.empty();
			}

			@Override
			public Optional<Watchable> redo() {
				pWatchable.setWatchList(pWatchList);
				aList.add(pWatchable);
				return Optional.empty();
			}

			@Override
			public void undo() {
				pWatchable.resetWatchList();
				aList.remove(pWatchable);
			}
		};
	}
	
	/**
	 * Retrieves and removes the Watchable at the specified index.
	 * 
	 * @param pIndex
	 *            the position of the Watchable to remove
	 * @pre pIndex < getTotalCount() && pIndex >= 0
	 */
	public Watchable removeWatchable(int pIndex) {
		assert pIndex < aList.size() && pIndex >= 0;

		Command removeWatchableCommand = createRemoveWatchableCommand(pIndex, aList.get(pIndex),this);

		toUndoCommands.push(removeWatchableCommand);

		// empty commands to redo when executing a command
		toRedoCommands = new Stack<>();
		toRedoCommands.push(removeWatchableCommand);

		return removeWatchableCommand.execute().get();

	}

	private Command createRemoveWatchableCommand(int pIndex, Watchable pWatchable, WatchList pWatchList) {
		return new Command() {
			final Watchable oldWatchable = pWatchable;

			@Override
			public Optional<Watchable> execute() {
				if (aNext > pIndex) {
					aNext--;
				}

				// remove link between watchable and watchlist
				oldWatchable.resetWatchList();
				return Optional.of(aList.remove(pIndex));
			}

			@Override
			public Optional<Watchable> redo() {
				oldWatchable.resetWatchList();
				return Optional.of(aList.remove(pIndex));
			}

			@Override
			public void undo() {
				oldWatchable.setWatchList(pWatchList);
				aList.add(oldWatchable);
			}
		};
	}
	
	/**
	 * @return the total number of valid watchable elements
	 */
	public int getValidCount() {
		int count = 0;
		for (Watchable item : aList) {
			if (item.isValid()) {
				count++;
			}
		}
		return count;
	}
	
	@Override
	public int getTotalCount() {
		return aList.size();
	}
	
	@Override
	public int getRemainingCount() {
		return aList.size() - aNext;
	}
	
	@Override
	public Watchable next() {
		assert getRemainingCount() > 0;
		Command nextCommand = createNextCommand();
		toUndoCommands.push(nextCommand);

		// empty commands to redo when executing a command
		toRedoCommands = new Stack<>();
		toRedoCommands.push(nextCommand);

		return nextCommand.execute().get();
	}

	private Command createNextCommand() {
		return new Command() {
			@Override
			public Optional<Watchable> execute() {

				Watchable next = aList.get(aNext);
				aNext++;
				if (aNext >= aList.size()) {
					aNext = 0;
				}
				return Optional.of(next);
			}

			@Override
			public Optional<Watchable> redo() {
				return Optional.of(aList.get(aNext));
			}

			@Override
			public void undo() {
				aNext--;
				if (aNext < 0) {
					aNext = 0;
				}
			}
		};
	}
	
	@Override
	public void reset() {
		Command resetCommand = createResetCommand();
		resetCommand.execute();

		// empty commands to redo when executing a command
		toRedoCommands = new Stack<>();
		toRedoCommands.push(resetCommand);

		toUndoCommands.push(resetCommand);
	}

	private Command createResetCommand() {
		return new Command() {
			private final int oldNext = aNext;

			@Override
			public Optional<Watchable> execute() {
				aNext = 0;
				return Optional.empty();
			}

			@Override
			public Optional<Watchable> redo() {
				aNext = 0;
				return Optional.empty();
			}

			@Override
			public void undo() {
				aNext = oldNext;
			}
		};
	}
	
	@Override
	public Iterator<Watchable> iterator() {
		return Collections.unmodifiableList(aList).iterator();
	}

	public void undo() {
		// no more actions on the stack
		if (toUndoCommands.empty()) {
			return;
		}
		Command aCommand = toUndoCommands.pop();
		aCommand.undo();
		toRedoCommands.push(aCommand);
	}

	public Optional<Watchable> redo() {
		// no more actions on the stack
		if (toRedoCommands.empty()) {
			return Optional.empty();
		}
		Command aCommand = toRedoCommands.pop();
		toUndoCommands.push(aCommand);
		return aCommand.redo();
	}
}
