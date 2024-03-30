package com.igorcma.workshopmongo.config;

import com.igorcma.workshopmongo.domain.Post;
import com.igorcma.workshopmongo.domain.User;
import com.igorcma.workshopmongo.repository.PostRepository;
import com.igorcma.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... arg0) throws Exception{

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        User igor = new User(null, "Igor Karl", "igor@gmail.com");

        Post post1 = new Post(null, sdf.parse("20/03/2018"), "Travel to São Paulo!", "in this week I will be going to São Paulo!", maria);
        Post post2 = new Post(null, sdf.parse("22/03/2018"), "Real madrid", "this is the best team from europe!", igor);

        userRepository.saveAll(Arrays.asList(maria, alex, bob, igor));
        postRepository.saveAll(Arrays.asList(post1, post2));
    }
}