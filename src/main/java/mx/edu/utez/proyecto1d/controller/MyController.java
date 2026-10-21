package mx.edu.utez.proyecto1d.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1d.controller.dto.RequestBodyDto;
import mx.edu.utez.proyecto1d.controller.dto.RequestCalculadoraDTO;
import mx.edu.utez.proyecto1d.controller.dto.ResponseCalculadoraDTO;
import mx.edu.utez.proyecto1d.service.MyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/my-services")
public class MyController {

    private final MyService myService;

    public MyController(MyService myService){
        this.myService = myService;
    }

    @GetMapping
    public String miPrimerServicio(){
        System.out.printf("Hello world");
        return "Hello world";
    }

    @GetMapping("/segundo-servicio")
    public String servicio2(){
        return "Este es mi segundo servico";
    }

    @GetMapping("/path-variable/{id}")
    public String pathVariable(@PathVariable String id){
            System.out.println("El id es "+id);
            return "buen id "+id;
    }

    @PostMapping("/request-body")
    //Request entity, es una clase que permite personalizar la respuesta que se manda al cliente
    public ResponseEntity<RequestBodyDto> requestBody(@RequestBody RequestBodyDto payload){
        System.out.println(payload.getNombre());
        System.out.println(payload.getEdad());
        System.out.println(payload.getCorreo());

        return ResponseEntity
                .status(201)
                .header("Content-Type", "application/json")
                .body(payload);
    }

    @PostMapping("/calculadora")
    public ResponseEntity<ResponseCalculadoraDTO> calculadora(@RequestBody @Valid RequestCalculadoraDTO payload) {
        ResponseCalculadoraDTO respuesta = myService.calculadora(payload);

        return ResponseEntity.ok(respuesta);
    }


}
