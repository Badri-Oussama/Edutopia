package com.example.forum_edutopia.Service;

import com.example.forum_edutopia.Repository.CommentRepository;
import com.example.forum_edutopia.Repository.LikeRepository;
import com.example.forum_edutopia.Repository.NotifRepo;
import com.example.forum_edutopia.Repository.PostRepository;
import com.example.forum_edutopia.Repository.ReplyRepository;
import com.example.forum_edutopia.Repository.UserRepository;
import com.example.forum_edutopia.Service.Exeptions.PostNotFoundException;
import com.example.forum_edutopia.entities.Comment;
import com.example.forum_edutopia.entities.Likee;
import com.example.forum_edutopia.entities.Notif;
import com.example.forum_edutopia.entities.Post;
import com.example.forum_edutopia.entities.Reply;
import com.example.forum_edutopia.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.*;


@Service
    @Slf4j
    public class ForumService  implements IforumService{
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    NotifRepo nr ;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ReplyRepository ReplyRepository;



    @Autowired

    PostRepository postRepository;

    @Override
    public List<Post> findAll(String votecount) {
    	 return  postRepository.findAll(Sort.by(votecount).descending());
    }

    public void save(Post post) {
        if(post.getContenu()!=null) {
        int totalPostCount = (int) postRepository.count();
        post.setVoteCount(totalPostCount == 0 ? 1 : totalPostCount + 1);
        postRepository.save(post);}
    }

    @Override
    public void addPostAttachedFile(int IdPost, String attachedFile) {
        Post post = postRepository.findById(IdPost).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setAttached_file(attachedFile);
        postRepository.save(post);
    }

    @Override
    public void addComAttachedFile(int idCom, String attachedFile) {
        Comment comment = commentRepository.findCommentByIdCom(idCom);
        comment.setAttached_file(attachedFile);
        commentRepository.save(comment);
    }

    @Override
    public  void updatePostAttachedFile(int IdPost, String attachedFile) {
        Optional<Post> optionalPost = postRepository.findById(IdPost);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setAttached_file(attachedFile);
            postRepository.save(post);
        } else {
            throw new PostNotFoundException("no post available!");
        }
    }

    public List<Comment> fetchall() {
        return commentRepository.findAll();
    }




    public Optional<Comment> findcommmentById(int IdPost) {
        return commentRepository.findById(IdPost);
    }
    public Optional<Post> findpostbyid(int IdPost){

        return postRepository.findById(IdPost);

    }


    @Override
    public void save(Comment comment , int id, int idp ) {
        //controle de saisie
    	if(comment.getContenuCom()!=null) {
        String filterComplaint = filterComplaint(comment.getContenuCom());
        
        comment.setUser(userRepository.findById(id).get());
        comment.setPost(postRepository.findById(idp).get());
        Notif n = new Notif();
        n.setPost(postRepository.findById(idp).get());
        n.setComment(userRepository.findById(id).get());
        n.setUser(postRepository.findById(idp).get().getUser());
        nr.save(n);
        comment.setContenuCom(filterComplaint);
    
         commentRepository.save(comment);}
    }
    @Override
    public List<Notif> listenotif(int id){
    	return userRepository.findById(id).get().getNotifs();
    }
    @Override
	public List<Reply> replies(int id){
    	
    	return  commentRepository.findById(id).get().getReplies();
    }
    @Override
    public void save (Reply rep , int id, int idp) {
        //controle de saisie
    	if(rep.getContenu()!=null) {
    		
    	//instance lel
    	 Notif n = new Notif();
         n.setPost(postRepository.findById(idp).get());
         n.setComment(userRepository.findById(id).get());
         n.setUser(postRepository.findById(idp).get().getUser());
         nr.save(n);
    	  rep.setUser(userRepository.findById(id).get());
          rep.setComment(commentRepository.findById(idp).get());
          rep.setContenu(rep.getContenu());
         ReplyRepository.save(rep);
    	}
    	
    }
    @Override
    public void deleteById(int id) {
        commentRepository.deleteById(id);
    }



    public void deletePostById(int id) {
        postRepository.deleteById(id);
    }

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Post> searchPosts(String searchTerm) {
        String query = "SELECT p FROM Post p WHERE p.title LIKE :searchTerm OR p.contenu LIKE :searchTerm OR p.category LIKE :searchTerm";
        TypedQuery<Post> typedQuery = entityManager.createQuery(query, Post.class);
        typedQuery.setParameter("searchTerm", "%" + searchTerm + "%");
        return typedQuery.getResultList();
    }


    @Transactional
    public void incrementPostView(int postId) {
        Optional<Post> optionalPost = findpostbyid(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setViews(post.getViews() + 1);
            postRepository.save(post);
        }
    }

    @Override
    public void addLikeToPost(int IdPost, int userId) {

            Post post = postRepository.findById(IdPost).orElse(null);
            User user = userRepository.findById(userId).orElse(null);
            Likee likee = new Likee();
            likee.setPost(post);
            likee.setUser(user);
            likee.setEtat(true);
            post.setNblike(post.getNblike() + 1) ;
            likeRepository.save(likee);

        }
    @Override
    public void addDisLikeToPost(int IdPost, int userId) {

        Post post = postRepository.findById(IdPost).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        Likee likee = new Likee();
        likee.setPost(post);
        likee.setUser(user);
        likee.setEtat(false);

        post.setNbdislike(post.getNbdislike() -1 ) ;
        likeRepository.save(likee);

    }

    @Override
    public int getNumberOfLikesForPost(int IdPost) {
        Post post = postRepository.findById(IdPost).orElse(null);
        return post.getNblike();
    }

    @Override
    public int getNumberOfdisLikesForPost(int IdPost) {
    	 Post post = postRepository.findById(IdPost).orElse(null);
         return post.getNbdislike();
    }

    public void setVoteCount(int totalPostCount) {
        if (totalPostCount == 0) {
            this.setVoteCount(1);
        } else {
            this.setVoteCount(totalPostCount + 1);
        }
    }
    private final String[] badwords ={"merde","salop","exemple"};

    public String filterComplaint(String complaint) {
        for (String badWord : badwords) {
            int wordStart = complaint.indexOf(badWord);
            while (wordStart >= 0) {
                int wordEnd = wordStart + badWord.length() - 1;
                String filteredWord = badWord.charAt(0) + repeat("*", badWord.length() - 2) + badWord.charAt(badWord.length() - 1);
                complaint = complaint.substring(0, wordStart) + filteredWord + complaint.substring(wordEnd + 1);
                wordStart = complaint.indexOf(badWord, wordEnd + 1);
            }
        }
        return complaint;
    }

    private String repeat(String s, int n) {
        return String.join("", Collections.nCopies(n, s));
    }
   /* public Post giveALike(Long id){
        Post post = postRepository.getById(id);
        post.setVoteCount(post.getVoteCount()+1);
        return postRepository.save(post);
    }
    public Post giveADisLike(Long id){
        Post post = postRepository.getById(id);
        post.setVoteCount(post.getVoteCount()-1);
        return postRepository.save(post);
    }*/

	@Override
	public void voter(int id) {

        Post post = postRepository.findById(id).get();
        if(post.getVoteCount()==null)
        {
        	post.setVoteCount(1);
        }else {
        post.setVoteCount(post.getVoteCount()+1);
        }
        postRepository.save(post);
		
	}



	@Override
	public void unvoter(int id) {
	       Post post = postRepository.findById(id).get();
	       if(post.getVoteCount()==null)
	        {
	        	post.setVoteCount(0);
	        }else {
	        post.setVoteCount(post.getVoteCount()-1);
	        }
	        postRepository.save(post);
		
	}








    /*public int getNumberOfLikesForPost(int postId) {
        return likeRepository.countByPostId(postId);
    }*/
 /*@Override
    public static Map<Integer, Integer> getNumberOfLikesForPosts(List<Integer> postIds) {
        Map<Integer, Integer> likesByPost = new HashMap<>();
        List<Object[]> results = likeRepository.countLikesForPosts(postIds);
        for (Object[] result : results) {
            Integer postId = (Integer) result[0];
            Long numberOfLikes = (Long) result[1];
            likesByPost.put(postId, numberOfLikes.intValue());
        }
        return likesByPost;
    }*/
    }






