import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AttriputebindingComponent } from './attriputebinding/attriputebinding.component';
import { ClassbindingComponent } from './classbinding/classbinding.component';
import { DatabindingComponent } from './databinding/databinding.component';
import { DeptComponent } from './dept/dept.component';
import { EceComponent } from './dept/ece/ece.component';
import { MechComponent } from './dept/mech/mech.component';
import { DirectivesComponent } from './directives/directives.component';
import { EventbindingComponent } from './eventbinding/eventbinding.component';
import { PropertybindongComponent } from './propertybindong/propertybindong.component';
import { StylebindingComponent } from './stylebinding/stylebinding.component';

const routes: Routes = [
  { path: 'classbinding', component: ClassbindingComponent },
  {path:'attripte',component: AttriputebindingComponent},
  { path:'propertybinding', component: PropertybindongComponent },
  { path:'eventbinding', component: EventbindingComponent },
  { path:'directives', component: DirectivesComponent },

  {path :'stylebinding',component:StylebindingComponent},
  {path :'databinding',component:DatabindingComponent},
  {path:'parant',component:DeptComponent, children:[
    {path:'childmech',component:MechComponent},
    {path:'childEce', component:EceComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
