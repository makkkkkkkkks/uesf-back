package org.ua.uesf.service.auth.impl;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.ua.uesf.config.jwt.JwtProvider;
import org.ua.uesf.exception.messages.AlreadyExistException;
import org.ua.uesf.exception.messages.NotFoundException;
import org.ua.uesf.mapper.UserMapper;
import org.ua.uesf.model.Role;
import org.ua.uesf.model.User;
import org.ua.uesf.model.dto.user.AuthRequest;
import org.ua.uesf.model.dto.user.AuthResponse;
import org.ua.uesf.model.dto.user.RegistrationDto;
import org.ua.uesf.resository.RoleRepository;
import org.ua.uesf.resository.UserRepository;
import org.ua.uesf.service.auth.AuthService;
import org.ua.uesf.service.user.UserService;

@AllArgsConstructor

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
        private final BCryptPasswordEncoder bcryptpasswordencoder;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);


    public AuthResponse register(RegistrationDto registrationDto) {
        if (userService.findByLogin(registrationDto.getUserName()) != null) {
            throw new AlreadyExistException("User with username " + registrationDto.getUserName() + " already exist");
        }
        User user = userMapper.fromDtoToUser(registrationDto);
        String regPass = registrationDto.getPassword();
        String confirmPass = registrationDto.getConfirmPassword();
        if (regPass.equals(confirmPass)) {
            Role userRole = roleRepository.findByName("ROLE_USER");
            user.setRole(userRole);
            user.setPassword(bcryptpasswordencoder.encode(regPass));
            userRepository.save(user);
        }
              String token = jwtProvider.generateToken(registrationDto.getUserName());
        return new AuthResponse(token);

    }

    @Override
    public AuthResponse auth(AuthRequest authRequest) {
        User user = userService.findByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword());
        if(user==null){
            throw new NotFoundException("Cant find user with login " + authRequest.getLogin());
        }
        String token = jwtProvider.generateToken(user.getUserName());
        return new AuthResponse(token);
    }

    @Override
    public void login() {

    }

    @Override
    public void forgotPassword() {

    }

    @Override
    public void deleteAccount() {

    }

    @Override
    public void updateAccount() {

    }

 /*
     * @purpose: for login to the user.
     *//*
    @Override
    public Response loginUser(LoginDto logindto, String token) {
        System.out.println("token::: "+token);
        String email = jwt.getUserToken(token);
        //User User=repository.findByemail(email);

        if (email != null) {
            ModelMapper mapper = new ModelMapper();
            User user = mapper.map(logindto, User.class);

            if (email.equals(logindto.getEmail())) {
                //System.out.println(email);
                boolean isValid = logindto.getPassword().equals(user.getConfirmPassword());
                //System.out.println(isValid);
                if (isValid) {
                    bcryptpasswordencoder.encode(logindto.getPassword());
                    repository.save(user);
                    return new Response(200, "LOGIN", HttpStatus.OK);
                }
            }
        }
        return new Response(400, "iINVALID_CREDENTIAL", HttpStatus.BAD_REQUEST);
    }

    *//**
     * @purpose: if the password is forget, then how to send a confirmation to the
     *           mail
     * @return: the token is send to the mail id or not
     *//*
    @Override
    public Response forgetPassword(ForgetPasswordDto forgetpassword) {
        //User email = repository.findByemail(forgetpassword.getEmail());
        String token = jwt.createToken(forgetpassword.getEmail());
        //	if (email != null) {
        //jms.sendMail(forgetpassword.getEmail(), token);
        return new Response(200, "SENDING_TOKEN", HttpStatus.OK);
        //}
        //return new Response(400, "INVALID_MAIL_ID", HttpStatus.BAD_REQUEST);

    }

    *//**
     * @purpose: to reset the password
     * @return: the password is reset or not.
     *//*
    @Override
    public Response resetPassword(ResetPasswordDto resetPassworddto, String token) {
        String resetPassToken = jwt.getUserToken(token);
        User user = repository.findByEmail(resetPassToken);

        if (user != null) {
            String newPass = resetPassworddto.getNewPassword();
            String confirmPass = resetPassworddto.getConfirmPassword();

            if (newPass.equals(confirmPass)) {
                user.setPassword(bcryptpasswordencoder.encode(resetPassworddto.getNewPassword()));
                user.setConfirmPassword(resetPassworddto.getConfirmPassword());
                repository.save(user);
                return new Response(200, "RESET_PASSWORD", HttpStatus.OK);
            }
        }
        return new Response(400, "PASSWORD_NOT_MATCHED", HttpStatus.BAD_REQUEST);
    }

    *//**
     * @purpose: to add the profile picture in the list
     * @return: it returns the confirmation that picture is uploaded or the
     *          extension is valid or the mail id is valid or not
     *
     *//*
    @Override
    public Response profilePic(String token, MultipartFile file) throws IOException {
        String email = jwt.getUserToken(token);
        User user = repository.findByEmail(email);
        if (user != null) {
            if (file.getOriginalFilename().contains(".jpg") || file.getOriginalFilename().contains(".jpeg")
                    || file.getOriginalFilename().contains(".png")) {
                File convertFile = new File("/home/admin1/Desktop" + file.getOriginalFilename());
                convertFile.createNewFile();
                FileOutputStream fileOpStrm = new FileOutputStream(convertFile);
                String pic = "/home/admin1/Desktop" + file.getOriginalFilename();
                user.setProfilePic(pic);
                repository.save(user);
                fileOpStrm.write(file.getBytes());
                fileOpStrm.close();
                return new Response(200, environment.getProperty("PIC_UPLOAD"), HttpStatus.OK);
            }
            return new Response(400, environment.getProperty("INCORRECT_EXTENSION"), HttpStatus.BAD_REQUEST);
        }
        return new Response(400, environment.getProperty("INVALID_MAIL_ID"), HttpStatus.BAD_REQUEST);
    }

    *//**
     * @purpose: to delete the valid profile picture
     * @return: the profile picture is deleted or not
     *//*
    @Override
    public Response deletePic(String token) {
        String email = jwt.getUserToken(token);
        User user = repository.findByEmail(email);
        if (user != null) {
            user.setProfilePic(null);
            repository.save(user);
            return new Response(200, environment.getProperty("PIC_DELETED"), HttpStatus.OK);
        }
        return new Response(400, environment.getProperty("INVALID_CREDENTIAL"), HttpStatus.BAD_REQUEST);
    }

    *//**
     * @purpose: to edit the profile picture
     * @return: the confirmation of profile picture is uploaded or the extension is
     *          valid or not and the previous profile pic is available or not
     *//*
    @Override
    public Response editPic(String token, MultipartFile file) throws IOException {
        String email = jwt.getUserToken(token);
        User user = repository.findByEmail(email);
        if (user != null) {
            String previousProfilePic = user.getProfilePic();
            if (previousProfilePic != null) {
                user.setProfilePic(null);
                if (file.getOriginalFilename().contains(".jpg") || file.getOriginalFilename().contains(".jpeg")
                        || file.getOriginalFilename().contains(".png")) {
                    File convertFile = new File("/home/admin1/Desktop" + file.getOriginalFilename());
                    convertFile.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
                    String pic = "/home/admin1/Desktop" + file.getOriginalFilename();
                    user.setProfilePic(pic);
                    repository.save(user);
                    fileOutputStream.write(file.getBytes());
                    fileOutputStream.close();
                    return new Response(200, environment.getProperty("EDIT_PROFILE_PIC"), HttpStatus.OK);
                }
                return new Response(400, environment.getProperty("INCORRECT_EXTENSION"), HttpStatus.BAD_REQUEST);
            }
            return new Response(400, environment.getProperty("PROFILE_PIC_NOT_EXISTS"), HttpStatus.BAD_REQUEST);
        }
        return new Response(400, environment.getProperty("INVALID_CREDENTIAL"), HttpStatus.BAD_REQUEST);
    }*/
}
