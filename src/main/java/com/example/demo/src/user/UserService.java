package com.example.demo.src.user;



import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.config.BaseResponseStatus.FAILED_TO_MODIFY_PASSWORD;

// Service Create, Update, Delete 의 로직 처리
@Service
public class UserService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserDao userDao;
    private final UserProvider userProvider;
    private final JwtService jwtService;


    @Autowired
    public UserService(UserDao userDao, UserProvider userProvider, JwtService jwtService) {
        this.userDao = userDao;
        this.userProvider = userProvider;
        this.jwtService = jwtService;

    }

    //POST
    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
        //14세 이상, 이용약관 동의, 개인정보 수집 동의 여부
        if(postUserReq.getOverFourteen()!=1 || postUserReq.getTos()!=1 || postUserReq.getAgreeTos()!=1){
            throw new BaseException(POST_USERS_DISAGREE_TOS);
        }
        //중복
        if(userProvider.checkEmail(postUserReq.getUserEmail()) ==1){
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        }

        System.out.println("3");
        String pwd;
        try{
            //암호화
            pwd = new SHA256().encrypt(postUserReq.getPassword());
            System.out.println(pwd);
            postUserReq.setPassword(pwd);

        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
        }
        System.out.println("dfvds");
        try{
            int userIdx = userDao.createUser(postUserReq);
            System.out.println(userIdx);
            //jwt 발급.
            String jwt = jwtService.createJwt(userIdx);
            return new PostUserRes(jwt,userIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }



    public void modifyUserName(PatchUserReq patchUserReq) throws BaseException {
        try{
            int result = userDao.modifyUserName(patchUserReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void modifyUserPwd(PatchUserPwdReq patchUserPwdReq) throws BaseException{
        String newPwd=patchUserPwdReq.getNewPwd();
        String newPwdValid=patchUserPwdReq.getNewPwdValidation();
        String encryptPwd;
        try {
            encryptPwd = new SHA256().encrypt(newPwd);
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }

        if(newPwdValid.equals(newPwd)){
            userDao.modifyUserPassword(patchUserPwdReq);
        }
        else{
            throw new BaseException(FAILED_TO_MODIFY_PASSWORD);
        }


    }
}
