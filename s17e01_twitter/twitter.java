class Tweet{
    id: int
    user: User
    texto: String
    likes: String[]
    darLike(userId){
        if(!likes.find(userId))
            likes.add(userId)
        else
            throw voce nao pode dar like duas vezes seu apelão
    }
}

class User{
    id: String
    seguidos: User[]
    seguidores: User[]
    myTweets: Tweet[]
    timeline: Tweet[]
    naoLido: int
--
    User(id){

    }

    darLike(msgId){
        try{
            msg = timeline.get(msgId)
        }catch{
            try{
                msg = myTweets.get(msgId)
            }catch(Exception e){
                throw e;
            }
        }
        msg.darLike(this.userId);
    }

    void seguir(outro: User){
        outro.seguidores(this);
        this.seguidor(outro);
    }

    enviarTweet(tweet){
        myTweets.add(tweet){
        for(User user : seguidor)
            user.timeline.add(tweet)
            user.naoLido++
        }
    }

}

Class TwitterGenerator{
    int nextId;
    rTweets: Rep<Tweet>
    Tweet generate(userId, texto){
        tweet = new Tweet(nextId++, userId, texto);
        rTweets.add(tweet);
        return tweet;
    }
}

class Controller {
    rUser: Repository<User>
    rTweets: Repository<Tweet>
    twgen: TwitterGenerator(rTweets);

    process(string line){
        if(cmd == "addUser"){//id
            rUser.add(ui[1], new User(ui[1]));
        }
        if(cmd =="tweet"){//_user _texto
            rUser.get(ui[1]).twittar(twgen(ui[1], texto));
        }

    }

}

