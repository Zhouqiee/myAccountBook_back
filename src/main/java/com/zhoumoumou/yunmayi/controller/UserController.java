package com.zhoumoumou.yunmayi.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhoumoumou.yunmayi.dto.LoginInfo;
import com.zhoumoumou.yunmayi.dto.UserInsertReq;
import com.zhoumoumou.yunmayi.dto.UserUpdateReq;
import com.zhoumoumou.yunmayi.entity.User;
import com.zhoumoumou.yunmayi.service.UserService;
import com.zhoumoumou.yunmayi.utils.JwtUtil;
import com.zhoumoumou.yunmayi.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * (User)表控制层
 *
 * @author zhouqie
 * @since 2023-12-15 10:08:13
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户管理接口")
@Validated
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    private AuthenticationManager authenticationManager;


    /**
     * 分页查询所有数据
     *
     * @param page    当前页数
     * @param perPage 页数大小
     * @param user    查询实体
     * @return 所有数据
     */
    @GetMapping
    @ApiOperation(value = "分页查询所有数据 该方法支持通过id获取 或者 多条件获取")
    public R selectAll(@ApiParam("当前页面") @RequestParam(defaultValue = "1") Integer page,
                       @ApiParam("获取数据个数") @RequestParam(defaultValue = "5") Integer perPage,
                       @ApiParam("用户信息") User user) {
        System.out.println(user);
//                通过id获取
        if (!ObjectUtils.isEmpty(user.getId())) {
            ArrayList<User> users = new ArrayList<>();
            users.add(this.userService.getById(user.getId()));
            return R.ok(users);
        } else {
            Page<User> outpage = new Page<>(page, perPage);
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

            if (StringUtils.hasText(user.getName())) {
                userQueryWrapper.eq("name", user.getName());
            }
            if (StringUtils.hasText(user.getUsername())) {
                userQueryWrapper.eq("username", user.getUsername());
            }
            if (StringUtils.hasText(user.getPassword())) {
                userQueryWrapper.eq("password", user.getPassword());
            }
            if (StringUtils.hasText(user.getPhone())) {
                userQueryWrapper.eq("phone", user.getPhone());
            }
            if (!Objects.isNull(user.getGender())) {
                userQueryWrapper.eq("gender", user.getGender());
            }
            return R.ok(userService.list(userQueryWrapper));
        }
    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
//    @ApiOperation(value = "通过id获取数据")
//    public R selectOne(@PathVariable @ApiParam("用户id") Serializable id) {
//        return R.ok(this.userService.getById(id));
//    }

    /**
     * @param req 插入数据集合
     * @return
     */
    @PostMapping
    @ApiOperation(value = "插入数据(支持单插和多插)")
    public R insert(@RequestBody @Valid UserInsertReq req) {
        System.out.println(req);
//        if (reqlist.size()==0){
//            throw new GlobalException("请不要尝试输入空数据");
//        }
//        List<User> collect = reqlist.stream().map(e -> e.toUser()).collect(Collectors.toList());
        Boolean flag = this.userService.save(req.toUser());

        if (true) {
            return R.ok().message("插入成功");
        } else {
            return R.fail().message("插入失败");
        }
    }


    /**
     * 修改数据
     *
     * @param req 实体对象
     * @return 修改结果
     */
    @PutMapping
    @ApiOperation(value = "更新数据")
    public R update(@RequestBody UserUpdateReq req) {
        Boolean flag = this.userService.updateById(req.toUser());
        if (flag == true) {
            return R.ok().message("修改成功");
        } else {
            return R.fail().message("修改失败");
        }
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @ApiOperation(value = "批量删除数据")
    public R delete(@RequestParam("idList") List<Long> idList) {
        Boolean flag = this.userService.removeByIds(idList);

        if (flag = true) {
            return R.ok().message("修改成功");
        } else {
            return R.fail().message("修改失败");
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除数据")
    public R delete(@PathVariable("id") int id) {
        boolean b = this.userService.removeById(id);
        if (b = true) {
            return R.ok().message("修改成功");
        } else {
            return R.fail().message("修改失败");
        }
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public R login(@RequestBody LoginInfo loginInfo) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginInfo.getUsername(),loginInfo.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//        将当前认证导入redis中用于后续登录时携带jwt时不拦截
        SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
        emptyContext.setAuthentication(authenticate);
        SecurityContextHolder.setContext(emptyContext);
        String token = JwtUtil.createJWT("zhouqie");
        return R.ok(token).message("登录成功");
    }
}

