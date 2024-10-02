package ar.com.globallogic.challenge.kencinas95.views;

import ar.com.globallogic.challenge.kencinas95.models.Member;
import ar.com.globallogic.challenge.kencinas95.services.MemberRegistrationService;
import ar.com.globallogic.challenge.kencinas95.services.MemberService;
import ar.com.globallogic.challenge.kencinas95.utils.ViewMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberView {
    private static final ViewMessage SUCCESSFUL_REGISTRATION_MESSAGE = ViewMessage.info("Registered!", "Registration successful");

    private final MemberService service;
    private final MemberRegistrationService registrationService;

    @GetMapping
    public String index(Model model) {
        model.asMap().clear();
        model.addAttribute("member", new Member());
        model.addAttribute("members", registrationService.getMembers());
        model.addAttribute("messages", List.of());
        return "layout";
    }

    @PostMapping("/views/members/register")
    public String register(@Validated @ModelAttribute Member member, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            try {
                service.register(member);
                model.addAttribute("member", new Member());
                model.addAttribute("messages", List.of(SUCCESSFUL_REGISTRATION_MESSAGE));
            } catch (Exception ex) {
                log.error("Registration failed: {}", ex.getMessage(), ex);
                final ViewMessage error = ViewMessage.error(
                        ViewMessage.getMessageFromException(ex),
                        "Registration unsuccessful"
                );
                model.addAttribute("messages", List.of(error));
            } finally {
                model.addAttribute("members", registrationService.getMembers());
            }
        }
        return "layout";
    }

}
