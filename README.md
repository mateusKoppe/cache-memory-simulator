# Cache Memory Simulator

The aim of this project is to simulate the behaviour of a cache memory that implements [set associative mapping](https://en.wikipedia.org/wiki/Cache_placement_policies) as cache placement policy and [least recently used](https://en.wikipedia.org/wiki/Cache_replacement_policies) (LRU) as replacement policy. Regarding memory writing, the strategy used was [write back](https://www.geeksforgeeks.org/write-through-and-write-back-in-cache/).
This project was developed as final assignment for Computer Organization class taught by [lcaimi](https://github.com/lcaimi) in the [Computer Science course at UFFS](https://cc.uffs.edu.br/).

### Memory settings

 - Cell size: 8 bits; 
 - Main memory size: 128 cells;
 - Block size: 4 cells;
 - Cache memory rows amount: 8;
 - Set size: 4 rows
 
### Running the application

Firstly install [jdk](https://www.java.com/pt-BR/download/) in your environment, and then [Apache Maven](https://maven.apache.org/download.cgi), the project/dependency manager tool used in the project. Once you get that done run the following command:

```bash
mvn exec:java -Dexec.mainClass="App"
```

### Run tests

To test all the project modules run:

```bash
mvn test
```
### Contributors

<p>
  <a href="https://github.com/mateusKoppe">
    <img style="border-radius:20px;" src="https://avatars.githubusercontent.com/u/12251731?s=400&u=b68b275aec43c92f8c33af9ee57570dd32db90c6&v=2" alt="koppe" width="75"/>
  </a>
  <a href="https://github.com/arufonsekun">
    <img src="https://avatars.githubusercontent.com/u/19571204?s=400&u=301d98d99590a96b541279749ae76b5175047145&v=4" alt="arufonsekun" width="75" />
  </a>
</p>
