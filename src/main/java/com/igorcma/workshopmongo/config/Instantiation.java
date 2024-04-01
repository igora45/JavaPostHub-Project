package com.igorcma.workshopmongo.config;

import com.igorcma.workshopmongo.domain.Post;
import com.igorcma.workshopmongo.domain.User;
import com.igorcma.workshopmongo.dto.AuthorDTO;
import com.igorcma.workshopmongo.dto.CommentDTO;
import com.igorcma.workshopmongo.repository.PostRepository;
import com.igorcma.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... arg0) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        User igor = new User(null, "Igor Karl", "igor@gmail.com");
        User reginaldo = new User(null, "Reginaldo folden", "reginaldo@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob, igor, reginaldo));

        CommentDTO c1 = new CommentDTO("São Paulo is great", sdf.parse("21/03/2018"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("My family lives in São Paulo", sdf.parse("22/03/2018"), new AuthorDTO(igor));
        CommentDTO c3 = new CommentDTO("Ei, good luck on this trip", sdf.parse("27/03/2018"), new AuthorDTO(bob));
        CommentDTO c4 = new CommentDTO("The best team ever", sdf.parse("24/03/2018"), new AuthorDTO(igor));
        CommentDTO c5 = new CommentDTO("Congrats, I'm so happy for you 😍", sdf.parse("02/04/2018"), new AuthorDTO(maria));
        CommentDTO c6 = new CommentDTO("This is a dream car, I hope you be happy with it!", sdf.parse("28/03/2018"), new AuthorDTO(bob));
        CommentDTO c7 = new CommentDTO("Thanks everyone!", sdf.parse("25/03/2018"), new AuthorDTO(igor));
        CommentDTO c8 = new CommentDTO("OMGGG, this is so good!", sdf.parse("01/03/2024"), new AuthorDTO(alex));
        CommentDTO c9 = new CommentDTO("I'm proud of you my brother, this is a dream 🙏", sdf.parse("01/03/2024"), new AuthorDTO(reginaldo));
        CommentDTO c10 = new CommentDTO("I want to see this car later, my friend", sdf.parse("01/03/2024"), new AuthorDTO(reginaldo));
        CommentDTO c11 = new CommentDTO("You will put pops and bangs ?", sdf.parse("01/03/2024"), new AuthorDTO(bob));

        Post post1 = new Post(null, sdf.parse("20/03/2018"), "Travel to São Paulo!", "in this week I will be going to São Paulo!", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("21/03/2018"), "Real madrid", "this is the best team from europe!", new AuthorDTO(maria));
        Post post3 = new Post(null, sdf.parse("22/03/2018"), "Buying the golf GTI 😍!", "I'm so happy, I think I will die because I am so happy", new AuthorDTO(igor));
        Post post4 = new Post(null, sdf.parse("01/04/2024"), "Stage 3 on my GOLF GTI!", "today I will be going to modify my car, and make it more powerful 🙅‍♂️", new AuthorDTO(igor));

        postRepository.saveAll(Arrays.asList(post1, post2, post3, post4));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        igor.getPosts().addAll(Arrays.asList(post3, post4));

        userRepository.saveAll(Arrays.asList(maria, igor));

        post1.getComments().addAll(Arrays.asList(c1, c2, c3));
        post2.getComments().add(c4);
        post3.getComments().addAll(Arrays.asList(c5, c6, c7));
        post4.getComments().addAll(Arrays.asList(c8, c9, c10, c11));

        postRepository.saveAll(Arrays.asList(post1, post2, post3, post4));

    }
}