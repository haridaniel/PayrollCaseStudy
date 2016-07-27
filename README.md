# PayrollCaseStudy

This is my implementation of Robert C. Martin's **Payroll-Case-Study** learning project presented in his book [Agile Software Development][agilebook], and in his [videos][solidcasestudyvideo] on [cleancoders.com][cleancoders.com]. 


[cleancoders.com]: http://cleancoders.com
[agilebook]: https://www.amazon.com/dp/0135974445/ref=pd_lpo_sbs_dp_ss_3?pf_rd_p=1944687462&pf_rd_s=lpo-top-stripe-1&pf_rd_t=201&pf_rd_i=0134494164&pf_rd_m=ATVPDKIKX0DER&pf_rd_r=K01QN9JYEFBK5T9W3JPX
[solidcasestudyvideo]: https://cleancoders.com/episode/clean-code-episode-14/show

# The clean Architecture #

The [clean architecture][cleanarch] separates concerns of the application in a scalable and maintainable way. 
[cleanarch]: https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html

Clean architecture systems are:

- Independent of Frameworks. The architecture does not depend on the existence of some library of feature laden software. This allows you to use such frameworks as tools, rather than having to cram your system into their limited constraints.
- Testable. The business rules can be tested without the UI, Database, Web Server, or any other external element.
- Independent of UI. The UI can change easily, without changing the rest of the system. A Web UI could be replaced with a console UI, for example, without changing the business rules.
- Independent of Database. You can swap out Oracle or SQL Server, for Mongo, BigTable, CouchDB, or something else. Your business rules are not bound to the database.
- Independent of any external agency. In fact your business rules simply don’t know anything at all about the outside world.

## [more info...](http://cleancodejava.com/uncle-bob-payroll-case-study-full-implementation/) ##
