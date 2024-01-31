package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/dish")
@Slf4j
/**
 * 菜品管理
 */
public class DishController {
    @Autowired
    private DishService dishService;
    @PostMapping()
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品 {}",dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * 分页查询菜品
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品分页查询 {}",dishPageQueryDTO);
        PageResult pageResult = dishService.query(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 菜品批量删除
     * @param ids
     * @return
     */
    @DeleteMapping()
    public Result delete(@RequestParam List<Long> ids){
        log.info("菜品删除 {}",ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }
}
