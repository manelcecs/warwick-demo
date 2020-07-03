import { Component, OnInit, OnDestroy, KeyValueDiffers } from "@angular/core";
import { SearchService } from "./services/search.service";
import { Subscription } from "rxjs";
import { SearchRequest, SearchResponse } from "./domain/search.domain";
import { FormControl, Validators, AbstractControl } from "@angular/forms";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent implements OnInit, OnDestroy {
  //Title of the app
  public title: string = "search keyword";

  //Any subcription will be attachet to this object and destroyed after use
  private subscription = new Subscription();

  //Last request
  request: SearchRequest;

  //example request
  private exampleRequest: SearchRequest = {
    keyWord: "in",
    text:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse molestie leo est, a tempus lectus accumsan ac. Nunc aliquam rhoncus nisl, et facilisis nunc viverra ac. Ut interdum lacus eu nunc bibendum rhoncus. Fusce feugiat vulputate augue id mollis. Suspendisse ut nulla pellentesque, tempor lacus non, sodales leo. In mauris augue, semper in arcu at, congue porttitor massa. Etiam in rhoncus ipsum, in maximus dolor. Fusce at ante at nisl eleifend convallis vel non dolor." +
      "Donec in mi ac velit congue fringilla at vel eros. Pellentesque eget massa non diam tempus facilisis ac vitae sapien. Quisque nisi ipsum, congue non fermentum sit amet, porttitor at lectus. Nunc ut sodales dolor. Integer hendrerit vestibulum viverra. Integer ut leo in elit tincidunt ultrices. Nullam ornare tellus vitae dolor tempor, consectetur sagittis quam sollicitudin. Nunc viverra ante est. Cras commodo commodo sapien, vitae accumsan sem dictum non. Duis vestibulum aliquet mi ac condimentum. Aliquam in risus in tortor dignissim malesuada. Pellentesque quis mauris in odio pharetra bibendum." +
      "Phasellus ut ex ut eros rutrum aliquam quis eu lacus. Nunc in blandit libero. Duis maximus felis leo. Aliquam auctor iaculis ligula sed ornare. Suspendisse at sagittis mauris. Maecenas ornare, nisl ornare condimentum tincidunt, eros tortor mollis quam, nec luctus purus sapien tristique libero. Donec nec ipsum tellus. Aliquam a aliquet tortor. Nullam pretium elit sit amet fringilla consequat. Pellentesque a felis sit amet odio malesuada tempor fringilla et ex. Cras imperdiet maximus venenatis. Praesent orci justo, ullamcorper vitae rhoncus fermentum, pellentesque non leo. In aliquam orci nec finibus lacinia." +
      "Maecenas vitae ante eget tortor gravida iaculis. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc vel elit vel nibh interdum egestas. Nunc a pulvinar sapien. Maecenas volutpat at nulla id maximus. Mauris vulputate, felis eu sagittis fermentum, dolor orci pulvinar neque, non luctus arcu arcu sed nunc. Vivamus commodo dapibus erat non laoreet. Curabitur iaculis, lacus ac ornare elementum, tortor nisi mollis quam, id semper metus nibh eget urna. Aliquam ultrices mattis facilisis. Sed vitae eleifend lacus. Aliquam dapibus neque eget erat ornare consequat. Nulla blandit justo magna, eu fringilla dui auctor vel. Integer hendrerit nec tortor aliquam iaculis. Nulla ut aliquam est. Nam rutrum, nulla a finibus varius, est velit gravida sem, non ultricies lectus odio tincidunt nisi." +
      "Pellentesque facilisis orci id metus aliquet, ac dictum quam tincidunt. Proin sodales nisi vel eleifend semper. Aenean lacinia dictum justo id aliquet. Morbi accumsan in mauris et luctus. Maecenas sit amet finibus augue, quis efficitur sem. Fusce imperdiet facilisis facilisis. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque consectetur elit id volutpat vulputate. Curabitur lacinia semper massa. Ut vel lorem lobortis, posuere ante ac, varius ex. Nam viverra tellus a arcu ornare, nec pretium dolor consectetur. Quisque egestas lorem a nibh malesuada, vel condimentum orci tempor. Ut id mi magna." +
      "Donec id ligula leo. Nulla ultricies ante eros, in pellentesque orci sodales quis. Donec est ante, fermentum ut tempus in, rhoncus non sapien. Nunc vitae mi laoreet, gravida eros ac, imperdiet augue. Praesent scelerisque facilisis sem ac facilisis. Nunc magna augue, vestibulum egestas posuere consequat, interdum vel nunc. Donec ut dui id mi convallis posuere vel id turpis." +
      "Aenean tellus quam, ullamcorper ut metus a, imperdiet eleifend nibh. In pellentesque venenatis lacus, at cursus neque tempus eu. Proin facilisis lobortis leo, vitae fringilla enim sagittis ac. Sed ultrices faucibus tellus. Curabitur in efficitur nibh. Vivamus lacinia magna id justo dignissim viverra. Ut turpis enim, tristique id lacus id, convallis sagittis velit." +
      "Morbi ac placerat ipsum. Aliquam vehicula tincidunt luctus. Sed malesuada, dui hendrerit ullamcorper sodales, nulla diam auctor sem, viverra venenatis sem ante vitae lorem. Maecenas volutpat fermentum est, at congue diam maximus et. Nullam feugiat magna in sapien semper porttitor. Vestibulum neque dolor, rhoncus id erat non, dapibus viverra felis. Donec lectus diam, ullamcorper ut molestie eget, rhoncus quis sem. Nulla tortor dolor, molestie in vehicula efficitur, mattis nec nisl. Nulla auctor porta metus eget pretium. Fusce volutpat sodales dui ut consequat. Integer at sem ac ex eleifend facilisis. Cras vitae justo maximus, sodales justo quis, porttitor dolor. Donec ullamcorper eros sed tellus iaculis semper. Nullam gravida lorem vel tincidunt efficitur." +
      "Morbi ultricies nulla vel ultrices mollis. Pellentesque eget velit eu sapien venenatis malesuada id et neque. Phasellus nec vehicula erat. Integer ultrices, lacus ultrices egestas consectetur, diam nibh hendrerit augue, venenatis bibendum dui nisl ac massa. Quisque vehicula ligula nisl, id sagittis ex varius vitae. Maecenas posuere sollicitudin ornare. Nam ipsum nulla, ultrices ut ex scelerisque, consequat finibus neque. Morbi viverra quis risus sed finibus. Maecenas vitae scelerisque mauris. Etiam imperdiet malesuada purus, ac malesuada sem dapibus quis. Donec a blandit odio, id lacinia lorem. In et massa orci. Quisque tempus porta nunc sed condimentum. In commodo efficitur leo, eu eleifend metus efficitur eget." +
      "In neque arcu, rutrum et enim vitae, rutrum consequat orci. Pellentesque nec quam in mauris dictum pellentesque et eget urna. Fusce bibendum, turpis ac auctor dignissim, nibh lacus porttitor ipsum, id mattis eros eros non nulla. Mauris sed sapien aliquet, sodales sapien vitae, accumsan dolor. Morbi vulputate, urna semper tempor lobortis, sem nisi volutpat ex, eget vehicula urna lacus non leo. Sed interdum rhoncus ultrices. Integer quis orci vel risus mollis sagittis id lobortis purus. Nulla finibus magna nec lectus aliquet, et vestibulum odio vestibulum. Suspendisse tincidunt dolor sit amet tortor elementum blandit. Integer luctus mi a congue luctus. Etiam vel tincidunt felis, non venenatis neque. Donec et lobortis neque. Donec mollis purus ut urna placerat, eu tincidunt tortor lacinia. Aenean a dolor dui. Mauris quis interdum sapien." +
      "Duis ac nisi urna. Vivamus eget maximus elit. Maecenas non placerat ex. Suspendisse lectus ipsum, maximus nec urna faucibus, ultricies suscipit ex. Cras tortor elit, placerat a lacinia id, feugiat nec massa. Aliquam eleifend accumsan magna nec viverra. Morbi vel neque sed turpis interdum sodales. Fusce ultrices dapibus ex, vitae tempus massa finibus vel." +
      "Pellentesque laoreet ante neque, eget placerat eros aliquam in. Nullam ligula turpis, imperdiet nec tellus nec, ultricies viverra sapien. Fusce nec lacus. ",
  };

  //FormControl Object
  keyWordControl: FormControl = new FormControl("", [
    Validators.required,
    this.validatorNotBlank,
    this.validatorOneWord,
  ]);
  textControl: FormControl = new FormControl("", [
    Validators.required,
    this.validatorNotBlank,
  ]);

  //Search Response
  response: SearchResponse;

  //constructor of component
  constructor(private searchService: SearchService) {}

  //Init method
  ngOnInit() {
    //Obtain the storage last search
    this.request = JSON.parse(localStorage.getItem("lastRequest"));
    //checking if the last request exist (request !== undefined)
    if (this.request) {
      //setings last request properties
      this.setSearchGroup();
    }

  }

  //Destroy method
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  onSubmit() {
    if (this.validForm()) {
      this.request = {
        keyWord: this.keyWordControl.value,
        text: this.textControl.value,
      };

      this.searchService.sendRequestToSplit(this.request).subscribe(
        (res: SearchResponse) => {
          //res !== undefined
          if (res) {
            this.response = res;
          }
        },
        (error) => {
          console.error("Something went wrong.");
          console.error(error);
        }
      );
      localStorage.setItem("method", "split");

      localStorage.setItem("lastRequest", JSON.stringify(this.request));
    }
  }

  validForm(): boolean {
    let valid = true;

    valid = valid && this.keyWordControl.valid;

    valid = valid && this.textControl.valid;

    return valid;
  }

  loadExample() {
    this.setSearchGroup();
  }

  private setSearchGroup() {
    //setting last request properties
    this.keyWordControl.setValue(this.request.keyWord);
    this.textControl.setValue(this.request.text);

    this.keyWordControl.updateValueAndValidity();
    this.textControl.updateValueAndValidity();
  }

  //returns the error message for text area
  getErrorMessageText() {
    return this.textControl.hasError("required")
      ? "The text must be filled"
      : this.keyWordControl.hasError("blank")
      ? "The keyword cannot be a whitespace"
      : "";
  }

  //returns error message of keyword
  getErrorMessageKeyword() {
    return this.keyWordControl.hasError("required")
      ? "The keyword must be filled"
      : this.keyWordControl.hasError("blank")
      ? "The keyword cannot be a whitespace"
      : this.keyWordControl.hasError("oneWord")
      ? "The keyword can only be one"
      : "";
  }

  //Method that must validate if the input is only whitespaces
  validatorNotBlank(control: AbstractControl): { [key: string]: boolean } {
    let input = control.value;

    if (input !== undefined && input.trim() === "") {
      return { blank: true };
    } else {
      return null;
    }
  }

  validatorOneWord(control: AbstractControl): { [key: string]: boolean } {
    let input = control.value;

    if (input !== undefined) {
      let words: string[] = input.split(" ");
      if (words.length > 1) {
        return { oneWord: true };
      } else {
        return null;
      }
    } else {
      return null;
    }
  }
}
