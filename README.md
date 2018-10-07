Objective: Write a web crawler and it should be limited to one domain. www.prudential.co.uk

	Conditions: 
		1. Crawler should be limited to one domain
		2. Starting URL : www.prudential.co.uk
		3. It should visit all the pages within domain but not visit external sites such as google.com/twitter.inc

	Output: 
		1. Should be showing links to same domain.
		2. Links to external URLs
		3. Also, links to static images for respective pages.

Approach: 
	1. Assuming on pages there are only URLs are there
	2. It should not accept other domain except listed above in an argument to our program
	3. once web page is loaded/connected then collect all the links in this page i.e. domain page
	4. we should maintain pagestoVisit and pagesVisted and a method to crawl and search the urls inside page.
	5. For output we shall crate a file(xls) having internal links list, external links list (google,twitter)
	
		
