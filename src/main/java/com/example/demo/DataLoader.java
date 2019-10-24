package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    SongRepository songRepository;

    @Override
    public void run(String... strings) throws Exception{
        Album album;


        album = new Album("Rock", "queen", "1984", "15", transactionRepository.findById(1), songRepository.findByAlbum());
      albumRepository.save(album);

      album = new Album("rock", "queen", "1934", "20", transactionRepository.findById(1), )

    }
}
