package com.example.g2_se1630_swd392.security.jwt;

import com.example.g2_se1630_swd392.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Trong phương thức này, bạn cần truy vấn cơ sở dữ liệu hoặc bất kỳ nguồn dữ liệu nào khác để tìm kiếm thông tin người dùng.

        // Ví dụ: Truy vấn người dùng từ cơ sở dữ liệu bằng username
        var user = userRepository.findByEmailAndActiveTrue(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Tạo một đối tượng UserDetails từ thông tin người dùng lấy từ cơ sở dữ liệu
        // Trong trường hợp này, User là lớp mô hình của bạn
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getName(),
                // Có thể cấu hình quyền của người dùng ở đây, ví dụ: user.getRoles()
                // Đảm bảo rằng bạn trả về một danh sách các quyền phù hợp
                Collections.emptyList()
        );
    }
}
