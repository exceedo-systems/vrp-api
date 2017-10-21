package com.exceedo.vrpapi.security;

import java.io.PrintWriter;
import java.security.Principal;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.exceedo.vrpapi.domain.ApiUser;
import com.exceedo.vrpapi.domain.UserType;
import com.exceedo.vrpapi.domain.VrpJob;
import com.exceedo.vrpapi.repository.ApiUserRepository;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class VrpApiAuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private ApiUserRepository apiUserRepository;

	public ApiUser getApiClientByUsername(String username) {
		ApiUser apiUser = apiUserRepository.findOne(username);
		if (apiUser != null) {
			return apiUser;
		} else {
			return null;
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String apiKey = ((HttpServletRequest) request).getHeader("x-api-key");
		Principal principal = ((HttpServletRequest) request).getUserPrincipal();
		ApiUser apiUser = getApiClientByUsername(principal.getName());
		if (!apiUser.getApiKey().equals(apiKey)) {
		        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		        PrintWriter writer = response.getWriter();
		        writer.println("HTTP Status 403 - Invalid API Key");
		        return false;
		}

		String requestUri = ((HttpServletRequest) request).getRequestURI();
		log.info("URI - " + requestUri);
		int adminServiceIndex = requestUri.indexOf("/admin/");
		if(adminServiceIndex >= 0) {
			if(apiUser.getUserType() != UserType.ADMIN) {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			    PrintWriter writer = response.getWriter();
			    writer.println("HTTP Status 403 - You are not allowed to access admin services");
			    return false;
			}
		}else {
			
			int index = requestUri.indexOf("/job/");

			if (index >= 0) {
				String requestUriPartial = requestUri.substring(index + 1);
				log.info("requestUriPartial - " + requestUriPartial);
				String[] uriTokens = requestUriPartial.split("/");
				if (uriTokens.length > 1) {
					final String jobId = uriTokens[1];
					log.info("jobId - " + jobId);
					Stream<VrpJob> jobStream = apiUser.getJobs().stream();
					boolean clientJob = jobStream.anyMatch(job -> job.getId().equals(jobId));
					if (!clientJob) {
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					    PrintWriter writer = response.getWriter();
					    writer.println("HTTP Status 403 - You are not allowed to access Job -> " + jobId);
					    return false;
					}
				}
			}
			
			
		}
		

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
		
	}

}