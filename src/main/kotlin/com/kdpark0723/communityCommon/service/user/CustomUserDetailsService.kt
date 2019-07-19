package com.kdpark0723.communityCommon.service.user

import com.kdpark0723.communityCommon.model.user.dataAccess.UserDataAccessor
import com.kdpark0723.communityCommon.model.user.dataTransfer.UserPrincipal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomUserDetailsService @Autowired constructor(private val userDataAccess: UserDataAccessor) : UserDetailsService {

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(usernameOrEmail: String): UserDetails {
        // Let people login with either username or email
        val user = userDataAccess.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
            ?: throw UsernameNotFoundException("User not found with username or email : $usernameOrEmail")

        return UserPrincipal.create(user)
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    fun loadUserById(id: Long): UserDetails {
        val user = userDataAccess.findById(id)
            ?: throw UsernameNotFoundException("User not found with id : $id")

        return UserPrincipal.create(user)
    }
}