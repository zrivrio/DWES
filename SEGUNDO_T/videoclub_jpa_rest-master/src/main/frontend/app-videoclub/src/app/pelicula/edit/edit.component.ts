import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PeliculaService} from "../pelicula.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Pelicula} from "../pelicula";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
/*
  id: number = 0;
  pelicula: Pelicula = { id: 0, titulo: "VOID"};
  form: FormGroup =   new FormGroup({
    pelicula:  new FormControl('', [ Validators.required, Validators.pattern('^[a-zA-ZÁáÀàÉéÈèÍíÌìÓóÒòÚúÙùÑñüÜ \-\']+') ])
  });

  constructor(
    public categoriaService: PeliculaService,
    private route: ActivatedRoute,
    private router: Router
  ) { }
*/
  ngOnInit(): void {
   /* this.id = this.route.snapshot.params['idCategoria'];
    this.categoriaService.find(this.id).subscribe((data: Pelicula)=>{
      this.pelicula = data;

      this.form.get('titulo')?.setValue(this.pelicula.titulo);


    });
    */
  }
/*
  get f(){
    return this.form.controls;
  }

  submit(){
    console.log(this.form.value);
    this.categoriaService.update(this.id, this.form.value).subscribe(res => {
      console.log('Categroría actualizada satisfactoriamente!');
      this.router.navigateByUrl('categoria/index').then();
    })
  }
*/
}
