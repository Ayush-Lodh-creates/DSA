package concurrency.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

interface HtmlParser {
    public List<String> getUrls(String url);
}

public class WebCrawlerMultithreading {

    private String hostname;
    private ConcurrentHashMap<String, Boolean> visitedUrls = new ConcurrentHashMap<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    private AtomicInteger numsOfUrlToParse = new AtomicInteger(0);
    private HtmlParser htmlParser;

    class Task implements Runnable {
        private String url;

        Task(String url) {
            this.url = url;
        }

        public void run() {
            for(String extractedUrl : htmlParser.getUrls(url)) {
                String curHostName = extractedUrl.split("/")[2];
                if(curHostName.equals(hostname) && visitedUrls.putIfAbsent(extractedUrl, true) == null) {
                    numsOfUrlToParse.addAndGet(1);
                    executorService.submit(new Task(extractedUrl));
                }
            }
            numsOfUrlToParse.addAndGet(-1);
        }
    }

    public List crawl(String startUrl, HtmlParser htmlParser) throws InterruptedException {
        hostname = startUrl.split("/")[2];
        this.htmlParser = htmlParser;
        visitedUrls.put(startUrl, true);
        numsOfUrlToParse.addAndGet(1);
        executorService.submit(new Task(startUrl));
        while(numsOfUrlToParse.get() > 0) {
            Thread.sleep(80);
        }
        executorService.shutdown();
        return new ArrayList<>(visitedUrls.keySet());
    }
}
