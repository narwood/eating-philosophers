# eating-philosophers
Nicole Arwood and David Ona

Each Philosopher is represented by a Philosopher object, which encapsulates their name and a reference to their left fork, right fork, and the lock. The Philosopher class includes methods for thinking, taking both forks, eating, and dropping both forks.

Each fork is represented by a binary semaphore. When a philosopher wishes to pick up a fork, they try to acquire its permit; when they wish to drop a fork, they release its permit. This ensures only one philsopher can be holding any given fork at any given time.

The spaghetti is not directly represented by an object in our algorithm: there is an endless supply, and our philosophers can continue to think and eat in perpetuity. 

A philospher's phase of life is represented by the function their thread is executing. Each thread runs a never-ending loop cycling through think(), take(), eat(), and drop(). While the thread runs think() they are thinking, they are hungry and waiting to eat during take(), eating during eat(), and getting ready to return back to thinking during drop().

The algorithm prevents deadlocks by using semaphores to represent the forks and using a lock to protect a critical section of the code. Since a philosopher must acquire the lock to attempt to take their forks and will not release the lock until both forks are acquired, no philosopher will ever hold only one fork. Each philosopher holds either 2 or 0 forks at any given time - they either are actively eating, or they hold no shared resources (forks). A philosopher with 2 forks will eat for some time and then release their forks; at that time a hungry philosopher next to them will be able to take the shared fork and eat. 

The algorithm prevents starvation by using a fair ReentrantLock. This guarantees that threads will acquire the lock in the order that they requested it, allowing philosophers to essentially get in line to acquire their forks and guaranteeing that every philosopher gets a chance to eat.


