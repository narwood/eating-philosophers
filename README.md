# eating-philosophers

Nicole Arwood and David Ona

Each Philosopher is represented by a Philosopher object, which encapsulates their name and a reference to their left fork, right fork, and the lock. The Philosopher class includes methods for thinking, taking both forks, eating, and dropping both forks.

Each fork is represented by a binary semaphore. When a philosopher wishes to pick up a fork, they try to acquire its permit; when they wish to drop a fork, they release its permit. This ensures only one philsopher can be holding any given fork at any given time.

The spaghetti is not directly represented by an object in our algorithm: there is an endless supply, and our philosophers can continue to think and eat in perpetuity.

A philospher's phase of life is represented by the function their thread is executing. Each thread runs a never-ending loop cycling through think(), take(), eat(), and drop(). While the thread runs think() they are thinking, they are hungry and waiting to eat during take(), eating during eat(), and getting ready to return back to thinking during drop().

The algorithm prevents deadlocks by using semaphores to represent the forks and using a mutex lock to protect the critical section of the code that allows philosophers to pick up forks. Since a philosopher must acquire the lock to attempt to take their forks and will not release the lock until both forks are acquired, no philosopher will ever be stuck holding only one fork for eternity. If a philosopher is only able to pick up one fork, it will simply wait until the other fork becomes available and proceed with eating. While the philosopher is waiting for a given fork, no other philosophers will be able to pick up that same fork since the philosopher who is waiting is in sole posession of the lock that allows for fork pickup. Therefore, the philosopher is guaranteed to pick up the second fork without waiting for eternity. A philosopher with 2 forks will eat for some time and then release their forks; at that time, a hungry philosopher next to them will be able to take the shared fork and eat.

The algorithm prevents starvation by using a fair ReentrantLock (a Java implementation of a mutex lock). This guarantees that threads (philosophers) will acquire the lock in the order that they requested it, allowing philosophers to essentially get in line to acquire their forks and guaranteeing that every philosopher gets a chance to eat.

The table is represented by our main method where an array of semaphores (forks) as well as the philosophers (threads) are initialized. The threads are run to represent the lifecycles of the philosophers.
