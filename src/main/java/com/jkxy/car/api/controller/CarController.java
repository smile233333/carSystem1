package com.jkxy.car.api.controller;

import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("findAll")
    public JSONResult findAll() {
        List<Car> cars = carService.findAll();
        return JSONResult.ok(cars);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public JSONResult findById(@PathVariable int id) {
        Car car = carService.findById(id);
        return JSONResult.ok(car);
    }

    /**
     * 通过车名查询
     *
     * @param carName
     * @return
     */
    @GetMapping("findByCarName/{carName}")
    public JSONResult findByCarName(@PathVariable String carName) {
        List<Car> cars = carService.findByCarName(carName);
        return JSONResult.ok(cars);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById/{id}")
    public JSONResult deleteById(@PathVariable int id) {
        carService.deleteById(id);
        return JSONResult.ok();
    }

    /**
     * 通过id更新全部信息
     *
     * @return
     */
    @PostMapping("updateById")
    public JSONResult updateById(Car car) {
        carService.updateById(car);
        return JSONResult.ok();
    }

    /**
     * 通过id增加
     *
     * @param car
     * @return
     */
    @PostMapping("insertCar")
    public JSONResult insertCar(Car car) {
        carService.insertCar(car);
        return JSONResult.ok();
    }

    /**
     * 通过车名模糊查询
     * 查询指定范围
     *
     * @param carName
     * @return
     */
    @GetMapping("findByCarNameLike/{carName}/{start}/{end}")
    public JSONResult findByCarNameLike(@PathVariable String carName, @PathVariable int start, @PathVariable int end) {
        List<Car> cars = carService.findByCarNameLike(carName, start, end);
        return JSONResult.ok(cars);
    }

    /**
     * 通过id和vesion更新库存
     *
     * @return
     */
    @PostMapping("updateByIdAndVersion")
    public JSONResult updateByIdAndVersion(Car car) {
        carService.updateByIdAndVersion(car);
        return JSONResult.ok();
    }

    /**
     * 对车系查询系统中的车辆进行购买，购买后数据库内的车辆数量减少
     * 在客户购买车辆时，如果某一个型号车辆剩余数量少于客户购买数量，
     * 需要考虑交易问题，避免多位客户购买同一辆车
     * num 为购买数量
     * @return
     */
    @PostMapping("buyCar")
    public JSONResult buyCar(String carName, Integer num) {

        List<Car> cars = carService.findByCarName(carName);

        if (cars.size() > 0) {
            Integer carStock = cars.get(0).getCarStock();
            Integer version = cars.get(0).getVersion();
            if (num <= carStock) {
                for (Car item : cars) {
                    if (version == item.getVersion()) {
                        int temp = item.getCarStock();
                        item.setCarStock(temp - num);
                        carService.updateByIdAndVersion(item);
                    }
                }
                return JSONResult.ok(cars);
            } else {
                return JSONResult.ok("您购买的车型数量已超出库存");
            }
        } else {
            return JSONResult.ok("购买的车型不在系统内");
        }
    }
}
