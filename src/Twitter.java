import java.util.*;

/**
 * Created by burak on 11/26/2016.
 */
public class Twitter {
    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(2,5);
        twitter.postTweet(1,3);
        twitter.postTweet(1,101);
        twitter.postTweet(2,13);
        twitter.postTweet(2,10);
        twitter.postTweet(1,2);
        twitter.postTweet(2,94);
        twitter.postTweet(2,505);
        twitter.postTweet(1,333);
        twitter.postTweet(1, 22);
        List<Integer> newsFeed = twitter.getNewsFeed(2);
        twitter.follow(2, 1);
        newsFeed = twitter.getNewsFeed(2);
        System.out.println(Arrays.toString(newsFeed.toArray()));
    }
    // userId -> set of followed follows
    private Hashtable<Integer, Set<Integer>> follows;
    // userId -> set of tweets
    private Hashtable<Integer, Stack<Tweet>> tweets;
    private static int curOrderNo = 1;

    /** Initialize your data structure here. */
    public Twitter() {
        follows = new Hashtable<>();
        tweets = new Hashtable<>();
    }

    private class Tweet {
        private int id;
        private int order;
        public Tweet(int id) {
            this.id = id;
            this.order = Twitter.curOrderNo++;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!tweets.containsKey(userId)) {
            initUser(userId);
        }
        tweets.get(userId).push(new Tweet(tweetId));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by follows who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        if (!tweets.containsKey(userId)) {
            initUser(userId);
        }
        Comparator<Tweet> tweetComparator = new TweetComparator();
        PriorityQueue<Tweet> feedTweets = new PriorityQueue<>(10, tweetComparator);
        for (Integer followeeId : follows.get(userId)) {
            for (Tweet tweet : tweets.get(followeeId)) {
                feedTweets.offer(tweet);
            }
        }
        while (feedTweets.size() > 10) {
            feedTweets.remove();
        }
        LinkedList<Integer> tweetIds = new LinkedList<>();
        while (feedTweets.size() != 0 && tweetIds.size() < 10) {
            tweetIds.addFirst(feedTweets.remove().getId());
        }
        return (List<Integer>) tweetIds;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!follows.containsKey(followerId)) {
            initUser(followerId);
        }
        if (!follows.containsKey(followeeId)) {
            initUser(followeeId);
        }
        follows.get(followerId).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        if (!follows.containsKey(followerId)) {
            initUser(followerId);
        }
        if (follows.get(followerId).contains(followeeId)) {
            follows.get(followerId).remove(followeeId);
        }
    }

    private void initUser(int userId) {
        follows.put(userId, new HashSet<Integer>());
        follows.get(userId).add(userId);
        tweets.put(userId, new Stack<Tweet>());
    }

    private class TweetComparator implements Comparator<Tweet> {
        @Override
        public int compare(Tweet o1, Tweet o2) {
            return o1.order - o2.order;
        }
    }
}
